package com.example.testFramework.Helper;

import com.example.testFramework.Commons.ServiceImpl;
import com.example.testFramework.Constants.Service;
import com.example.testFramework.Constants.URI;
import io.restassured.response.Response;

public class CommonServiceHelper extends ServiceImpl {

    public Response getResponse(){
        Response result = get(Service.POSTMAN, URI.get);
        return result;
    }


}
