package com.example.testFramework.Constants;

import java.util.HashMap;
import java.util.Map;

public class Local implements EnvironmentMaster {

    private static Map<Service,String> map = new HashMap<>();

    private static Map<Service, String> getMap() {
        map.put(Service.TEST,"http://localhost:7060/service/");
        return map;
    }

    @Override
    public String getUrl(Service service) {
        return getMap().get(service);
    }
}
