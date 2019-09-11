package scouting;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

;

public class TestJson {
    public static void main(String[] args) throws Exception {
        String event_key = "2018isde2";
        String tba_base_url = "https://www.thebluealliance.com/api/v3/";
        String request_url = tba_base_url + "event/" + event_key + "/matches";


        HttpResponse<JsonNode> response = Unirest.get(request_url)
                .header("accept", "application/json")
                .queryString("X-TBA-Auth-Key", "XTONkkO0b81Cuof2WPFV1LUAXdO1OOv2rq0lq23xenS0lWDiEsxVtQQRo3REWkYp")
                .asJson();

        JSONArray arr = response.getBody().getArray();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject ev = arr.getJSONObject(i);
            int matchNumber = (Integer) ev.get("match_number");

            List<Object> red = new ArrayList<>();
            List<Object> blue = new ArrayList<>();

            for (Object team : ev.getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys").toList()) {
                DatabaseManager.get().getEventMatchRepository().create(new EventMatch(ev.getString("comp_level"),
                        matchNumber, 0, Integer.parseInt(((String) team).replaceAll("\\D+", ""))));
            }
            for (Object team : ev.getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys").toList()) {
                DatabaseManager.get().getEventMatchRepository().create(new EventMatch(ev.getString("comp_level"),
                        matchNumber, 1, Integer.parseInt(((String) team).replaceAll("\\D+", ""))));
            }

            System.out.println(red);
            System.out.println(blue);
        }
    }
}
