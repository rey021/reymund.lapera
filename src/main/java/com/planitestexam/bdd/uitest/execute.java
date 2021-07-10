package com.planitestexam.bdd.uitest;

import java.util.List;

public class execute {
    SeleniumFlow SE;
    getConfig config;
    LogManager  log;

    {
        config = new getConfig();
        log = new LogManager();
        log = config.logManager();

        try {
            config.initializeConfig();
            log.info("initialize webElement");
            config.initializeWebElements();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initializeDriver(){
        SE = new SeleniumFlow();
        SE.initialized(config);

        log.initialize(config.getLogType(), config.getConfigDIR());
    }

    public void run() throws Exception {

            initializeDriver();
            //SE.closeDriver();

        }//End of method run

    public getConfig getConfig(){
        return config;
    }

    public SeleniumFlow getSeleniumFlow(){
        return SE;
    }
} // End of method execute
