package com.planitestexam.bdd.uitest;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public execute() {
    }

    void initializeDriver(){
        SE = new SeleniumFlow();
        SE.initialized(config);

        log.initialize(config.getLogType(), config.getConfigDIR());
    }

    public void run() {

            initializeDriver();
            //SE.closeDriver();

        }//End of method run

    public getConfig getConfig(){
        return config;
    }

    public SeleniumFlow getSeleniumFlow(){
        return SE;
    }

// Added sample ===============================



    // Added sample ===============================


} // End of method execute
