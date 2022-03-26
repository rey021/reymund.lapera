package com.planitestexam.bdd.uitest;

public class TestBase {
    execute instance = execute.getInstance();
    GetConfig config = GetConfig.getInstance();
    SeleniumFlow SE = SeleniumFlow.getInstance();

    public void setUpApplication(){
        instance.run();
        config = instance.getConfig();
        SE = instance.getSeleniumFlow();
       // webelements = config.readFileElements();
    }
}
