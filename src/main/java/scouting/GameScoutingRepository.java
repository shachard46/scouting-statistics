package scouting;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new GameScouting(rs.getInt("game_id"), rs.getInt("team_id"), rs.getInt("prop_id"),
                rs.getString("prop_value"));
    }

    private List<GameScouting> getAllEntitiesByTeam(int teamId) {
        return getEntitiesByQuery(
                String.format("select * from TeamScouting where team_id=%d order by prop_id", teamId));
    }

    public List<Integer> getAllTeamGameNumbers(int teamId) throws RuntimeException {
        return selectElements("select distinct game_id from TeamScouting where team_id=" + teamId + ";", rs -> rs.getInt("game_id"));

    }

    public List<Integer> getAllTeamNumbers() throws RuntimeException {
        return selectElements("select distinct team_id from TeamScouting;", rs -> rs.getInt("team_id"));
    }

    private List<GameScouting> filterEntitiesByGameId(List<GameScouting> gameScoutings, int gameId) {
        List<GameScouting> gameGameScoutings = getEntitiesByQuery(
                String.format("SELECT * FROM TeamScouting where game_id=%d order by game_id;", gameId));
        List<GameScouting> newList = new ArrayList<>();
        for (GameScouting gameScouting : gameScoutings) {
            for (GameScouting propGameScouting : gameGameScoutings) {
                if (gameScouting.toString().equals(propGameScouting.toString())) {
                    newList.add(propGameScouting);
                }
            }
        }
        return newList;
    }

    private HashMap<String, String> getPropAvarageByTeam(int teamId) {
        HashMap<String, String> pAvg = new HashMap<>();
        selectElements(String.format("select prop_id, prop_avg from avgs where team_id=%d;", teamId), rs -> {
            pAvg.put(String.valueOf(rs.getInt("prop_id")), String.valueOf(rs.getString("prop_avg")));
            return pAvg;
        });
        return pAvg;
    }
    // create view avgs as SELECT team_id, prop_id, avg(prop_value) as prop_avg FROM
    // db.TeamScouting group by team_id, prop_id;

    private HashMap<String, String> getPropsAvarageByTeam(int teamId) {
        HashMap<String, String> avgs = new HashMap<>();
        avgs.put("teamId", String.valueOf(teamId));
        for (String propId : getPropAvarageByTeam(teamId).keySet()) {
            String propAvg = getPropAvarageByTeam(teamId).get(propId);
            propAvg = propAvg.contains(".0") ? propAvg.substring(0, propAvg.indexOf(".")) : propAvg;

            String propType = DatabaseManager.get().getGameScoutingPropsRepository()
                    .getEntityByPropId(Integer.parseInt(propId)).getPropType();
            propAvg = propAvg.length() > 4 && propAvg.contains(".") ? propAvg.substring(0, 4) : propAvg;
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
        List<Integer> teamIds = getAllTeamNumbers();
        List<HashMap<String, String>> teamBars = new ArrayList<>();
        for (Integer teamId : teamIds) {
            teamBars.add(getPropsAvarageByTeam(teamId));
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

    public void exportToTablet(String gameId, String compLevel) throws Exception {
        int i = 1;
        for (EventMatch team : DatabaseManager.get().getEventMatchRepository().getTeamsByGameAndCompType(gameId, compLevel)) {
            Map<String, String> root;
            if (getEntitiesByQuery("select * from TeamScouting where team_id=" + team.getTeamId()).isEmpty()) {
                root = DatabaseManager.get().getGameScoutingRepository().getPropsAvarageByTeam(2630);
                for (String k : root.keySet()) {
                    root.replace(k, "-");
                }
            } else {
                root = DatabaseManager.get().getGameScoutingRepository().getPropsAvarageByTeam(team.getTeamId());

            }
            root.put("team", String.valueOf(team.getTeamId()));
            root.put("game", gameId);
            root.put("color", team.getAlliance() == 0 ? "#E34234" : "#4166f5");

            Map<String, String> newMap = new HashMap<>();
            for (String k : root.keySet()) {
                try {
                    Integer.parseInt(k);
                    newMap.put("id_" + k, root.get(k));
                } catch (NumberFormatException e) {
                    newMap.put(k, root.get(k));
                }
            }
            root = newMap;
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
            FileTemplateLoader loader = new FileTemplateLoader(new File("src/main/webapp/WEB-INF/templates"));
            cfg.setTemplateLoader(loader);
            Template template = cfg.getTemplate("tabletTemp.html");
            Writer out = new FileWriter("/Users/shachardavid/table" + i + ".html");
            template.process(root, out);
            out.flush();
            out.close();
            Runtime.getRuntime().exec(new String[]{"zsh", "/K", "Start"});
            Runtime.getRuntime().exec("adb push /Users/shachardavid/table" + i + ".html /storage/emulated/0/");
            i++;
        }

    }
}
