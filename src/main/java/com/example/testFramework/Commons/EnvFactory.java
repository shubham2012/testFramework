package com.example.testFramework.Commons;

import com.example.testFramework.Constants.EnvironmentMaster;
import com.example.testFramework.Constants.Local;
import com.example.testFramework.Constants.Personalized;
import com.example.testFramework.Constants.Prod;
import com.example.testFramework.Constants.QA;

public class EnvFactory {

    public static EnvironmentMaster getCurrentEnv(){

        Env currEnv = Environment.getEnvironment();
        switch (currEnv){
            case LOCAL: return new Local();
            case QA: return new QA();
            case PROD: return new Prod();
            case PERSONALIZED: return new Personalized();
            default: return new Local();
        }
    }
}
