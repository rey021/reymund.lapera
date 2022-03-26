package com.planitestexam.bdd.uitest;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class execute {
    SeleniumFlow SE = SeleniumFlow.getInstance();
    GetConfig config = GetConfig.getInstance();
    private static final Logger logger = LogManager.getLogger(execute.class);

    private static volatile execute instance = null;

    private execute() throws Exception {
        if(instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }
// Singleton design patter
    public static execute getInstance() {
        if(instance == null) {
            synchronized(execute.class) {
                if(instance == null) {
                    try {
                        instance = new execute();
                        instance.initialize();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }
        }
        return instance;
    }

    private void initialize(){
        try {
            config.initializeConfig();
            logger.info("initialize webElement");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void initializeDriver(){
        SE.initialized();
    }

    public void run() {

            initializeDriver();
            //SE.closeDriver();

        }//End of method run

    public GetConfig getConfig(){
        return config;
    }

    public SeleniumFlow getSeleniumFlow(){
        return SE;
    }

// Added sample ===============================



    // Added sample ===============================


} // End of method execute
