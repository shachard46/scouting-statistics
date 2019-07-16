package scouting;

import org.json.simple.JSONObject;

import java.util.*;

public class TestCompare {


    public static void main(String[] args) {
        List<Map<String,String>> list1 = new ArrayList<>();

        Map<String,String> map1 = new HashMap<>();
        map1.put("id_1", "1");
        map1.put("id_2", "2");
        Map<String,String> map2 = new HashMap<>();
        map2.put("id_1", "2");
        map2.put("id_2", "2");
        list1.add(map2);
        list1.add(map1);

        System.out.println(JSONObject.toJSONString(map1));
        list1.sort((Map<String,String> a, Map<String,String> b) -> {
            String id1a = a.get("id_1");
            String id1b = b.get("id_1");
            return id1a.compareTo(id1b);
        });


        System.out.println(list1);

    }
}
