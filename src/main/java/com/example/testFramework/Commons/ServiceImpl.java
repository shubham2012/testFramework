package com.example.testFramework.Commons;

import com.example.testFramework.Constants.EnvironmentMaster;
import com.example.testFramework.Constants.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
public abstract class ServiceImpl {

    protected  <T> T get(Service service, String url, Map<String, String> headers, Class<T> responseClass){
        HttpClient client = HttpClientBuilder.create().build();
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        HttpGet request = new HttpGet(completeUrl);
        headers.forEach((x,y)->request.addHeader(x,y));
        T objResponse;
        objResponse = callAndGetResponse(responseClass, client, request);
        return objResponse;
    }

    protected  <T> String getString(Service service, String url, Map<String, String> headers){
        HttpClient client = HttpClientBuilder.create().build();
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        HttpGet request = new HttpGet(completeUrl);
        headers.forEach((x,y)->request.addHeader(x,y));
        String objResponse;
        try {
            HttpResponse response = client.execute(request);
            objResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Unable to interact with service");
        }
        return objResponse;
    }

    protected  <T> T post(Service service, String url, Map<String, String> headers, String payload, Class<T> responseClass){
        HttpClient client = HttpClientBuilder.create().build();
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        HttpPost request = new HttpPost(completeUrl);

        headers.forEach((x,y)->request.addHeader(x,y));
        T objResponse = callAndGetResponse(responseClass, client, request,payload);
        return objResponse;
    }

    protected  <T> T put(Service service, String url, Map<String, String> headers, String payload, Class<T> responseClass) {
        HttpClient client = HttpClientBuilder.create().build();
        String completeUrl = getCompleteUrl(service, url);
        log.info(completeUrl);
        HttpPut request = new HttpPut(completeUrl);
        headers.forEach((x,y)->request.addHeader(x,y));
        T objResponse = callAndGetResponse(responseClass,client,request, payload);
        return objResponse;
    }

    protected String getCompleteUrl(Service service, String url) {
        EnvFactory envFactory = new EnvFactory();
        EnvironmentMaster currentEnv = envFactory.getCurrentEnv();
        String serviceUrl = currentEnv.getUrl(service);
        return serviceUrl+url;
    }

    private <T> T callAndGetResponse(Class<T> responseClass, HttpClient client, HttpRequestBase request) {
        HttpResponse response;
        T objResponse;
        try {
            response = client.execute(request);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);
            objResponse = objectMapper.readValue(response.getEntity().getContent(), responseClass);
            log.info(objResponse.toString());
            ((CloseableHttpClient) client).close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to interact with the service or some exception occurred");
        }
        return objResponse;
    }

    private <T> T callAndGetResponse(Class<T> responseClass, HttpClient client, HttpEntityEnclosingRequestBase request, String payload) {
        HttpResponse response;
        T objResponse;
        try {
            StringEntity entity = new StringEntity(payload);
            request.setEntity(entity);
            response = client.execute(request);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objResponse = objectMapper.readValue(response.getEntity().getContent(), responseClass);
            log.info(objResponse.toString());
            ((CloseableHttpClient) client).close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to interact with the service or some exception occurred");
        }
        return objResponse;
    }

    public static String getElement(String responseBody, String format, Boolean xmlType) {
        XmlPath xmlPath = null;
        JsonPath jsonPath = null;
        String result = "";

        try {
            if (xmlType) {
                xmlPath = new XmlPath(responseBody);
            } else {
                jsonPath = new JsonPath(responseBody);
            }
        } catch (Exception var7) {
            throw new RuntimeException("Unable to parse object");
        }

        if (xmlType) {
            result = xmlPath.get(format);
        } else {
            result = jsonPath.get(format).toString();
        }

        result = result.replace("[", "").replace("]", "");
        return result;
    }
}
