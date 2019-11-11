package com.example.testFramework.Constants;

import java.util.HashMap;
import java.util.Map;

public class QA implements EnvironmentMaster {

    private static Map<Service,String> map = new HashMap<>();

    private static Map<Service, String> getMap() {
        map.put(Service.TEMP,"http://lms.scmqa.myntra.com/service");
        return map;
    }

    @Override
    public String getUrl(Service service) {
        return getMap().get(service);
    }
}
