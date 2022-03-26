package com.planitestexam.bdd.hooks;


import com.planitestexam.bdd.uitest.GetConfig;
import com.planitestexam.bdd.uitest.SeleniumFlow;
import com.planitestexam.bdd.uitest.execute;
import cucumber.api.java.Before;


import java.util.HashMap;
import java.util.Map;

public class Hooks {
    SeleniumFlow SE = SeleniumFlow.getInstance();

    public Hooks() throws Exception {
    }

    @Before
    public void setUpApplication() throws Exception {

    }

//    @After
//    public void closeDriver() throws Exception {
//        SE.closeDriver();
//    }
}
