package com.example.testFramework.Commons;

import com.example.testFramework.Constants.EnvironmentMaster;
import com.example.testFramework.Constants.Service;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
public class ServiceImpl {

    /**
     * Pass comma separated path in it
     * it will return you the exact value of a particular element
     * @param data
     * @param path
     * @return
     */
    public static String getElement(String data, String path) {
        String[] pathArr = path.split(",");
        StringBuilder pathBuilder = new StringBuilder("$");
        for (String str : pathArr) {
            pathBuilder.append(String.format("[\'%s\']", str.trim()));
        }
        DocumentContext jsonContext = JsonPath.parse(data);
        return jsonContext.read(pathBuilder.toString());
    }

    /**
     * It take comma separated values and will return List
     * @param data
     * @param path
     * @return
     */
    public static List<String> getElementList(String data, String path) {
        String[] pathArr = path.split(",");
        StringBuilder pathBuilder = new StringBuilder("$");
        for (String str : pathArr) {
            pathBuilder.append(String.format("[\'%s\']", str.trim()));
        }
        DocumentContext jsonContext = JsonPath.parse(data);
        return jsonContext.read(pathBuilder.toString());
    }

    /**
     * This is to all the values in the path
     * returns list as all the values in that path
     * @param data
     * @param path
     * @return
     */
    public static List<String> getPath(String data, String path) {
        String[] pathArr = path.split(",");
        StringBuilder pathBuilder = new StringBuilder("$");
        for (String str : pathArr) {
            pathBuilder.append(String.format("[\'%s\']",str));
        }
        pathBuilder.append("[*]");
        DocumentContext jsonContext = JsonPath.parse(data);
        return jsonContext.read(pathBuilder.toString());
    }

    /**
     * This is generic getJsonPath
     * Please go through https://www.baeldung.com/guide-to-jayway-jsonpath to get gist of what all you can pass and can get as output
     * @param data
     * @param path
     * @return
     */
    public static Object getJsonPathGeneric(String data, String path) {
        DocumentContext jsonContext = JsonPath.parse(data);
        return jsonContext.read(path);
    }

    protected Response get(Service service, String url){
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        Response response = given()
                .when()
                .get(completeUrl);
        return response;
    }

    protected Response post(Service service, String url, String payload, String contentType){
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        Response response = given()
                .contentType(contentType)
                .body(payload)
                .when()
                .post(completeUrl);
        return response;
    }

    protected Response put(Service service, String url, String payload, String contentType){
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        Response response = given()
                .contentType(contentType)
                .body(payload)
                .when()
                .put(completeUrl);
        return response;
    }

    protected Response delete(Service service, String url){
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        Response response = given()
                .when()
                .delete(completeUrl);
        return response;
    }

    protected String getCompleteUrl(Service service, String url) {
        EnvFactory envFactory = new EnvFactory();
        EnvironmentMaster currentEnv = envFactory.getCurrentEnv();
        String serviceUrl = currentEnv.getUrl(service);
        return serviceUrl + url;
    }


}
