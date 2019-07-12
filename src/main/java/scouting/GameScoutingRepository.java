package scouting;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GameScoutingRepository extends AbstractEntityDatabase<GameScouting> {
    public static void main(String[] args) throws Exception{
        Runtime.getRuntime().exec(new String[] {"zsh", "/K", "Start"});
        Runtime.getRuntime().exec("mkdir shlomi");


//        System.out.println(DatabaseManager.get().getGameScoutingRepository().getPropsAvarageByTeam(2630));
    }

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
        return new GameScouting(rs.getInt("game_id"), rs.getInt("team_id"), rs.getInt("prop_id"),
                rs.getString("prop_value"));
    }

    public List<GameScouting> getAllEntitiesByTeam(int teamId) {
        return getEntitiesByQuery(
                String.format("select * from TeamScouting where team_id=%d order by prop_id", teamId));
    }

    public List<Integer> getAllTeamGameNumbers(int teamId) throws RuntimeException {
        return selectElements("select distinct game_id from TeamScouting where team_id="+ teamId +";", rs -> rs.getInt("game_id"));

    }

    public List<String> getAllTeamNumbers() throws RuntimeException {
        return selectElements("select distinct team_id from TeamScouting;", rs -> String.valueOf(rs.getInt("team_id")));
    }

    public List<GameScouting> filterEntitiesByGameId(List<GameScouting> gameScoutings, int gameId) {
        List<GameScouting> gameGameScoutings = getEntitiesByQuery(
                String.format("SELECT * FROM TeamScouting where game_id=%d order by game_id;", gameId));
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
    // create view avgs as SELECT team_id, prop_id, avg(prop_value) as prop_avg FROM
    // db.TeamScouting group by team_id, prop_id;

    public HashMap<String, String> getPropsAvarageByTeam(int teamId) {
        HashMap<String, String> avgs = new HashMap<>();
        avgs.put("teamId", String.valueOf(teamId));
        for (String propId : getPropAvarageByTeam(teamId).keySet()) {
            System.out.println(propId);
            String propAvg = getPropAvarageByTeam(teamId).get(propId);
            propAvg = propAvg.contains(".0") ? propAvg.substring(0, propAvg.indexOf(".")) : propAvg;

            String propType = DatabaseManager.get().getGameScoutingPropsRepository()
                    .getEntityByPropId(Integer.parseInt(propId)).getPropType();
            propAvg = propAvg.contains(".") && propAvg.length() > 4? propAvg.substring(0, 4) : propAvg;
            switch (propType) {
                case "number":
                    avgs.put(propId, propAvg);
                    break;
                case "text":
                    avgs.put(propId, "text");
                    break;
                case "boolean":
                    avgs.put(propId, String.format("%d/%d | %d%%", (int) (Double.parseDouble(propAvg) * getAllTeamGameNumbers(teamId).size()),
                    		getAllTeamGameNumbers(teamId).size(), (int) (Double.parseDouble(propAvg) * 100)));
                    break;
                    default:
                        avgs.put(propId, "fuckingshit");
            }
        }
        return avgs;
    }

    public List<HashMap<String, String>> getPropsAvarage() {
        List<String> teamIds = getAllTeamNumbers();
        List<HashMap<String, String>> teamBars = new ArrayList<>();
        for (String teamId : teamIds) {
            teamBars.add(getPropsAvarageByTeam(Integer.parseInt(teamId)));
        }
        return teamBars;
    }

    public List<HashMap<String, String>> getTeamPropsByGame(int teamId) {
        List<HashMap<String, String>> teamBars = new ArrayList<>();
        for (int i : getAllTeamGameNumbers(teamId)) {
            List<GameScouting> teamGames = filterEntitiesByGameId(getAllEntitiesByTeam(teamId), i);
            HashMap<String, String> teamBar = new HashMap<>();
            teamBar.put("teamId", String.valueOf(teamId));
            teamBar.put("gameId", String.valueOf(i));
            for (int h = 0; h <= teamGames.size() - 1; h++) {
                teamBar.put(String.valueOf(teamGames.get(h).getPropId()), teamGames.get(h).getPropValue());
            }
            teamBars.add(teamBar);
        }
        return teamBars;
    }

    public List<HashMap<String, String>> orderByPropId(String propId) {
        List<HashMap<String, String>> propsAvg = getPropsAvarage();
        String propType = DatabaseManager.get().getGameScoutingPropsRepository()
                .getEntityByPropId(Integer.parseInt(propId)).getPropType();
        for (int i = 0; i < propsAvg.size() - 1; i++) {
            for (int j = 0; j < propsAvg.size() - i; j++) {
                switch (propType) {
                    case "number":
                        if (Integer.parseInt(propsAvg.get(j).get(propId)) < Integer
                                .parseInt(propsAvg.get(j + 1).get(propId))) {
                            HashMap<String, String> temp = propsAvg.get(j);
                            propsAvg.get(j).putAll(propsAvg.get(j + 1));
                            propsAvg.get(j + 1).putAll(temp);
                        }
                        break;
                    case "text":
                        return propsAvg;
                    case "boolean":
                        if (Integer.parseInt(
                                propsAvg.get(j).get(propId).substring(propsAvg.get(j).get(propId).indexOf("|") + 1,
                                        propsAvg.get(j).get(propId).length() - 1)) < Integer
                                .parseInt(propsAvg.get(j + 1).get(propId).substring(
                                        propsAvg.get(j + 1).get(propId).indexOf("|") + 2,
                                        propsAvg.get(j + 1).get(propId).length() - 1))) {
                            HashMap<String, String> temp = propsAvg.get(j);
                            propsAvg.get(j).putAll(propsAvg.get(j + 1));
                            propsAvg.get(j + 1).putAll(temp);
                        }
                }
            }
        }
        return propsAvg;
    }
    public void pushToTablet() throws IOException {
        Runtime.getRuntime().exec(new String[] {"zsh", "/K", "Start"});
        Runtime.getRuntime().exec("mkdir shlomi");
    }
}
