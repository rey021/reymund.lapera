package com.planitestexam.bdd.uitest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class GetConfig {

	private static volatile GetConfig instance = null;
	private static final Logger logger = LogManager.getLogger(SeleniumFlow.class);
	
	String	driverpath = "",
			webDriver = "",
			systemProperty = "",
			url = "",
			time = "",
			outputDIR = "",
			outputFileName= "",
			date = "",
			startdate = "",
			enddate = "",
			toolsDIR = "",
			configDIR = "",
			logType = "",
			secretFile = "",
			web_elements_objects;
	
	String home_contactpagebutton = "",
			contactpage_submitbutton = "",
			contactPage_errorMessage = "";
	
	
	String configPath = "src/main/java/com/planitestexam/bdd/config/";

	Properties prop = new Properties();
	InputStream input = null;
	private static boolean doScreenshotCapture = true;

	private GetConfig(){
		if(instance != null) {
			throw new RuntimeException("Use getInstance() method to create");
		}
	}
	public static GetConfig getInstance() {
		if(instance == null) {
			synchronized(GetConfig.class) {
				if(instance == null) {
					try {
						instance = new GetConfig();
						instance.initializeConfig();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		}
		return instance;
	}

	public void initializeConfig () throws Exception, IOException 
	{
		
	
		try {

			logger.info(configPath + "configuration.txt");
			
			input = new FileInputStream(configPath + "configuration.txt");
			
			
			// load a properties file
			prop.load(input);
			System.out.println();

			logger.info("loading properties for credentials and url...");

			// get the property value and print it out

			driverpath = prop.getProperty("driverpath");
			logger.info("driverpath = " + driverpath);
			
			webDriver = prop.getProperty("driver");
			logger.info("webDriver = " + webDriver);

			systemProperty = prop.getProperty("systemproperty");
			logger.info("SystemProperty = " + systemProperty);
			
			time = prop.getProperty("waittime");
			logger.info("waiting time = " + time);
			
			url = prop.getProperty("url");
			logger.info("url = " + url);	
			
			outputDIR = prop.getProperty("outputDIR");
			logger.info("outputDIR = " + outputDIR);
			
			outputFileName = prop.getProperty("outputFileName");
			logger.info("outputFileName = " + outputFileName);

			toolsDIR = prop.getProperty("toolsDIR");
			logger.info("toolsDIR = " + toolsDIR);
			
			configDIR = prop.getProperty("configDIR");
			logger.info("configDIR = " + configDIR);
			
			logType = prop.getProperty("logType");
			logger.info("logType = " + logType);

			secretFile= prop.getProperty("secret");
			logger.info("secretFile= " + secretFile);

			web_elements_objects = prop.getProperty("web_elements_objects");
			logger.info("web_elements_objects = " + web_elements_objects);

		} catch (IOException ex) {
			logger.error("error ---> ");
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("error ---> ");
					e.printStackTrace();
				}
			}
		}
	}


	public static boolean getScreenshotCaptureStatus() {
		return doScreenshotCapture;
	}
	public String getDriverPath() {
		return driverpath;
	}
	
	public String getWebDriver() {
		return webDriver;
	}
	
	public String getUrl() {
		return url;
	}

	
	public String getWaitingTime() {
		return time;
	}
	
	public String getConfigDIR() {
		return configDIR;
	}
	
	public String getLogType() {
		return logType;
	}

	public String getSystemProperty() { return systemProperty; }


	public Map<String, String> readFileElements() throws IOException {
		String filePath = web_elements_objects;
		Map<String, String> map = new HashMap<String, String>();

		String line;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		while ((line = reader.readLine()) != null)
		{
			String[] parts = line.split("=", 2);
			if (parts.length >= 2)
			{
				String key = parts[0];
				String value = parts[1];
				map.put(key, value);
			} else {
				System.out.println("ignoring line: " + line);
			}
		}

		for (String key : map.keySet())
		{
			System.out.println(key + ":" + map.get(key));
		}
		reader.close();

		return map;
	}
}
