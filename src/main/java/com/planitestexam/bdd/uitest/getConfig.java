package com.planitestexam.bdd.uitest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class getConfig {

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
	
	LogManager log = new LogManager();
	Properties prop = new Properties();
	InputStream input = null;
	
	public void initializeConfig () throws Exception, IOException 
	{
		
	
		try {
			
			log.initialize("uitest", configPath);
			log.info(configPath + "configuration.txt");
			
			input = new FileInputStream(configPath + "configuration.txt");
			
			
			// load a properties file
			prop.load(input);
			System.out.println();
			
			log.info("loading properties for credentials and url...");

			// get the property value and print it out

			driverpath = prop.getProperty("driverpath");
			log.info("driverpath = " + driverpath);
			
			webDriver = prop.getProperty("driver");
			log.info("webDriver = " + webDriver);

			systemProperty = prop.getProperty("systemproperty");
			log.info("SystemProperty = " + systemProperty);
			
			time = prop.getProperty("waittime");
			log.info("waiting time = " + time);
			
			url = prop.getProperty("url");
			log.info("url = " + url);	
			
			outputDIR = prop.getProperty("outputDIR");
			log.info("outputDIR = " + outputDIR);
			
			outputFileName = prop.getProperty("outputFileName");
			log.info("outputFileName = " + outputFileName);

			toolsDIR = prop.getProperty("toolsDIR");
			log.info("toolsDIR = " + toolsDIR);
			
			configDIR = prop.getProperty("configDIR");
			log.info("configDIR = " + configDIR);
			
			logType = prop.getProperty("logType");
			log.info("logType = " + logType);

			secretFile= prop.getProperty("secret");
			log.info("secretFile= " + secretFile);

			web_elements_objects = prop.getProperty("web_elements_objects");
			log.info("web_elements_objects = " + web_elements_objects);

		} catch (IOException ex) {
			log.error("error ---> ");
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("error ---> ");
					e.printStackTrace();
				}
			}
		}
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
	
	public LogManager logManager () {
		return log;
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
