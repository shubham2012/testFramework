package com.example.testFramework.Helper;

import com.example.testFramework.Commons.ServiceImpl;
import com.example.testFramework.Commons.Utils;
import com.example.testFramework.Constants.Service;
import com.example.testFramework.Constants.URI;

public class PostmanService extends ServiceImpl {

    public String getResponse(){
        String result = getString(Service.TEST, URI.get, Utils.jsonHeader());
        return result;
    }
}
