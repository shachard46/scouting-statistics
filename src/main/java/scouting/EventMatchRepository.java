package scouting;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventMatchRepository extends AbstractEntityDatabase<EventMatch> {
    @Override
    protected String getEntityTableName() {
        return "EventMatches";
    }

    @Override
    protected EventMatch getDBEntity(EventMatch entity) {
        return entity;
    }

    @Override
    protected String insertEntitySQL(EventMatch entity) {
        return String.format(
                "INSERT INTO EventMatches(comp_level, match_id, alliance, TeamID) values ('%s', %d, %d, %d);",
                entity.getCompLevel(), entity.getGameId(), entity.getAlliance(), entity.getTeamId());
    }

    @Override
    protected String updateEntityRowSQL(EventMatch entity) {
        return null;
    }

    @Override
    protected EventMatch entityFromResultSet(ResultSet rs) throws SQLException {
        return new EventMatch(rs.getString("comp_level"), rs.getInt("match_id"), rs.getInt("alliance"),
                rs.getInt("teamId"));
    }

    public List<EventMatch> getTeamsByGameAndCompType(String gameId, String compLevel) {
        return getEntitiesByQuery("select * from EventMatches where match_id=" + gameId + " and comp_level='"
                + compLevel + "' order by alliance;");
    }

    public void importFromTBA(String eventKey) throws Exception {
        DatabaseManager.get().getEventMatchRepository().clearTable();
        String tba_base_url = "https://www.thebluealliance.com/api/v3/";
        String request_url = tba_base_url + "event/" + eventKey + "/matches";

        HttpResponse<JsonNode> response = Unirest.get(request_url).header("accept", "application/json")
                .queryString("X-TBA-Auth-Key", "XTONkkO0b81Cuof2WPFV1LUAXdO1OOv2rq0lq23xenS0lWDiEsxVtQQRo3REWkYp")
                .asJson();

        JSONArray arr = response.getBody().getArray();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject ev = arr.getJSONObject(i);
            int matchNumber = (Integer) ev.get("match_number");

            for (Object team : ev.getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys").toList()) {
                DatabaseManager.get().getEventMatchRepository().create(new EventMatch(ev.getString("comp_level"),
                        matchNumber, 0, Integer.parseInt(((String) team).replaceAll("\\D+", ""))));
            }
            for (Object team : ev.getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys").toList()) {
                DatabaseManager.get().getEventMatchRepository().create(new EventMatch(ev.getString("comp_level"),
                        matchNumber, 1, Integer.parseInt(((String) team).replaceAll("\\D+", ""))));
            }

        }
    }
}
