package com.example.testFramework.Commons;

public class Environment {

    //Initialize this to take care of your running ENVIRONMENT
    private static final Env ENVIRONMENT = Env.QA;

    public static Env getEnvironment() {
        return ENVIRONMENT;
    }

}
