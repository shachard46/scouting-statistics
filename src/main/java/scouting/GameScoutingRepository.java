package scouting;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameScoutingRepository extends AbstractEntityDatabase<GameScouting> {
    @Override
    protected String getEntityTableName() {
        return "TeamScouting";
    }

    @Override
    protected GameScouting getDBEntity(GameScouting entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(GameScouting entity) {
        return null;
    }

    @Override
    protected String updateEntityRowSQL(GameScouting entity) {
        return null;
    }

    @Override
    protected GameScouting entityFromResultSet(ResultSet rs) throws SQLException {
        return new GameScouting(rs.getInt("game_id"), rs.getInt("team_id"), rs.getInt("prop_id"), rs.getString("prop_value"));
    }

    public List<GameScouting> getAllEntitiesByTeam(int teamId) {
        return getEntitiesByQuery(String.format("select * from TeamScouting where team_id=%d order by prop_id", teamId));
    }

    public List<GameScouting> filterEntitiesByPropId(List<GameScouting> gameScoutings, int propId) {
        List<GameScouting> propGameScoutings = getEntitiesByQuery(String.format("select * from TeamScouting where prop_id=%d order by prop_id", propId));
        List<GameScouting> newGameScoutings = new ArrayList<GameScouting>();
        for (GameScouting gameScouting : gameScoutings) {
            for (GameScouting propGameScouting : propGameScoutings) {
                if (gameScouting.toString().equals(propGameScouting.toString())) {
                    newGameScoutings.add(propGameScouting);
                }
            }
        }
        return newGameScoutings;
    }

    public List<GameScouting> filterEntitiesByGameId(List<GameScouting> gameScoutings, int gameId) {
        List<GameScouting> gameGameScoutings = getEntitiesByQuery(String.format("SELECT * FROM TeamScouting where game_id=%d order by game_id;", gameId));
        List<GameScouting> newList = new ArrayList<GameScouting>();
        for (GameScouting gameScouting : gameScoutings) {
            for (GameScouting propGameScouting : gameGameScoutings) {
                if (gameScouting.toString().equals(propGameScouting.toString())) {
                    newList.add(propGameScouting);
                }
            }
        }
        return newList;
    }

    public HashMap<String, String> getPropAvarageByTeam(int teamId) {
        HashMap<String, String> pAvg = new HashMap<>();
        selectElements(String.format("select prop_id, prop_avg from avgs where team_id=%d;", teamId), rs -> {
            pAvg.put(String.valueOf(rs.getInt("prop_id")), String.valueOf(rs.getString("prop_avg")));
            return pAvg;
        });
        return pAvg;
    }

    public HashMap<String, String> getPropsAvarageByTeam(int teamId) {
        HashMap<String, String> avgs = new HashMap<>();
        for (String propId : getPropAvarageByTeam(teamId).keySet()) {
            String propAvg = getPropAvarageByTeam(teamId).get(propId);
            String propType = DatabaseManager.get().getGameScoutingPropsRepository().getEntityByPropId(Integer.parseInt(propId)).getPropType();
            switch (propType) {
                case "number":
                    avgs.put(propId, propAvg);
                    break;
                case "text":
                    avgs.put(propId, "text");
                case "boolean":
                    String.format("%d/%d | %d%%", Integer.parseInt(propAvg) * getAllGameNumbers().size(), getAllGameNumbers().size(),
                            (int) ((Double.parseDouble(propAvg)) * 100.0) / 100.0);
                    break;
            }
        }
        return null;
    }

    public String getAvarage(List<GameScouting> gameScoutings) {
        //create view avgs as SELECT team_id, prop_id, avg(prop_value) as prop_avg FROM db.TeamScouting group by team_id, prop_id;
        double avg = 0;
        int boolCount = 0;
        String propType = DatabaseManager.get().getGameScoutingPropsRepository().getEntityByPropId(gameScoutings.get(0).getPropId()).getPropType();
        for (GameScouting gameScouting : gameScoutings) {
            switch (propType) {
                case "number":
                    avg += Integer.parseInt(gameScouting.getPropValue());
                    break;
                case "text":
                    return "text";
                case "boolean":
                    if (gameScouting.getPropValue().equals("true")) {
                        boolCount += 1;
                    }
                    break;
            }
        }
        if (propType.equals("boolean")) {
            return String.format("%d/%d | %d%%", boolCount, gameScoutings.size(), ((boolCount * 10) / gameScoutings.size()) * 10);
        }
        avg = (int) ((avg / gameScoutings.size()) * 100) / 100.0;
        System.out.println(avg == (int) avg);
        String sAvg = String.valueOf(avg);
        return sAvg.contains(".0") ? sAvg.substring(0, sAvg.indexOf(".")) : sAvg;
    }

    public List<HashMap<Integer, String>> getPropsAvarage() {
        List<String> teamIds = getAllTeamNumbers();
        List<HashMap<Integer, String>> teamBars = new ArrayList<HashMap<Integer, String>>();
        for (String teamId : teamIds) {
            List<GameScouting> teamProps = getAllEntitiesByTeam(Integer.parseInt(teamId));
            HashMap<Integer, String> teamBar = new HashMap<Integer, String>();
            teamBar.put(-1, teamId);
            for (int i = 1; i <= teamProps.get(teamProps.size() - 1).getPropId(); i++) {
                List<GameScouting> teamProp = filterEntitiesByPropId(teamProps, i);
                System.out.println(teamProp);
                teamBar.put(teamProp.get(0).getPropId(), getAvarage(teamProp));
            }
            System.out.println(teamBar);
            teamBars.add(teamBar);
        }
        return teamBars;
    }

    public List<String> getAllTeamNumbers() throws RuntimeException {
        Connection connection = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<String> entities = new ArrayList<String>();
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select distinct team_id from TeamScouting;");
            while (rs.next()) {
                entities.add(String.valueOf(rs.getInt("team_id")));
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not create statement", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return entities;
    }

    public List<Integer> getAllGameNumbers() throws RuntimeException {
        Connection connection = getConnection();
        Statement st = null;
        ResultSet rs = null;
        List<Integer> entities = new ArrayList<Integer>();
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select distinct game_id from TeamScouting;");
            while (rs.next()) {
                entities.add(rs.getInt("game_id"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not create statement", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return entities;
    }

    public List<HashMap<Integer, String>> getAllTeamPropsByGame(int teamId) {
        List<HashMap<Integer, String>> teamBars = new ArrayList<HashMap<Integer, String>>();
        for (int i = getAllGameNumbers().get(0); i <= getAllGameNumbers().get(getAllGameNumbers().size() - 1); i++) {
            List<GameScouting> teamGames = filterEntitiesByGameId(getAllEntitiesByTeam(teamId), i);
            HashMap<Integer, String> teamBar = new HashMap<Integer, String>();
            teamBar.put(-2, String.valueOf(teamId));
            teamBar.put(-1, String.valueOf(i));
            for (int h = 0; h <= teamGames.size() - 1; h++) {
                teamBar.put(teamGames.get(h).getPropId(), teamGames.get(i).getPropValue());
            }
            teamBars.add(teamBar);
        }
        return teamBars;
    }


    public static void main(String[] args) {
        System.out.println(DatabaseManager.get().getGameScoutingRepository().getPropsAvarageByTeam(2630));
    }
}
