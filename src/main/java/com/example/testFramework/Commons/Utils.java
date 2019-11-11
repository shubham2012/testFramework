package com.example.testFramework.Commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {

    public static Map<String,String> jsonHeader(){
        return getMap("application/json");
    }

    public static Map<String,String> xmlHeader(){
        return getMap("application/xml");
    }

    private static Map<String, String> getMap(String s) {
        Map<String,String> map = new HashMap<>();
        map.put("Authorization", "Basic Ytpi");
        map.put("Accept", s);
        map.put("Content-Type", s);
        return map;
    }

    public static <T> String getStringFromObj(T in){
        ObjectMapper objectMapper = new ObjectMapper();
        String out;
        try {
            objectMapper.registerModule(new JodaModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            out = objectMapper.writeValueAsString(in);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to cast input to String class : "+ in);
        }
        return out;
    }

    public static String randomNum(int length) {

        Random random = new Random();
        int n = random.nextInt(10);
        for (int i = 0; i < length - 1; i++) {
            n = n * 10;
            n += random.nextInt(10);
        }
        String ar = Integer.toString(Math.abs(n));
        return ar;
    }

    public static String getDate() {
        DateTime date = new DateTime();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        String str = fmt.print(date);
        return str;
    }

    public static String addDate(int days) {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(cal.getTime());
    }
}
