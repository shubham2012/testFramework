package com.example.testFramework.Commons;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Map<String,String> jsonHeader(){
        return getMap("application/json");
    }


    private static Map<String, String> getBasicJsonHeader(){
        Map<String,String> map = new HashMap<>();
        map.put("Accept", "application/json");
        map.put("Content-Type", "application/json");
        return map;
    }

    private static Map<String, String> getMap(String s) {
        Map<String,String> map = new HashMap<>();
        map.put("Authorization", "Basic Ytpi");
        map.put("Accept", s);
        map.put("Content-Type", s);
        return map;
    }

}
