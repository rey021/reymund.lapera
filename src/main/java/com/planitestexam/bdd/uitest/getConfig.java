package com.planitestexam.bdd.uitest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class getConfig {
	
	String	username1 = "",
			username2 = "",
			password = "",
			driverpath = "",
			webDriver = "",
			systemProperty = "",
			url = "",
			time = "",
			outputDIR = "",
			outputFileName= "",
			stockListFile = "",
			FileHeader = "",
			date = "",
			startdate = "",
			enddate = "",
			toolsDIR = "",
			configDIR = "",
			logType = "",
			secretFile = "";
	
	String user1Element	= "",
			user2Element 	= "",
			passElement 	= "",
			frame1IDElement = "",
			quoteCSSElement = "",
			brokerHistoryCSSElement = "",
			submitElem = "",
			resultsTableElement = "";
	
	
	String configPath = "src/main/java/com/planitestexam/bdd/config/";
	String config = "";
	
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
			
			FileHeader = prop.getProperty("FileHeader");
			log.info("FileHeader = " + FileHeader);
			
			toolsDIR = prop.getProperty("toolsDIR");
			log.info("toolsDIR = " + toolsDIR);
			
			configDIR = prop.getProperty("configDIR");
			log.info("configDIR = " + configDIR);
			
			logType = prop.getProperty("logType");
			log.info("logType = " + logType);

			secretFile= prop.getProperty("secret");
			log.info("secretFile= " + secretFile);

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
	
	public void initializeWebElements () throws Exception, IOException 
	{
		Properties prop = new Properties();
		InputStream input = null;
	
		try {

			log.info(configPath + "webElements.txt");
			input = new FileInputStream(configPath + "webElements.txt");

			// load a properties file
			prop.load(input);
			
			System.out.println();
			log.info("loading properties for webElements...");

			// get the property value and print it out
			//user1Element = prop.getProperty("user1");
			//log.info("user1Element = " + user1Element);

			input.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} // End of initializeWebElements Method
	
	public String getUser1() {
		return username1;
	}
	
	public String getUser2() {
		return username2;
	}
	
	public String getPassword() {
		return password;
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
	
	public String getUser1Elem () {
		return user1Element;
	}
	
	public String getUser2Elem() {
		return user2Element;
	}
	
	public String getPassElem() {
		return passElement;
	}
	
	public String getFrame1IDElem() {
		return frame1IDElement;
	}
	
	public String getQuoteCSSElem() {
		return quoteCSSElement;
	}
	
	public String getBroHisCSSElem() {
		return brokerHistoryCSSElement;
	}
	
	public String getSubmitElem() {
		return submitElem;
	}
	
	public LogManager logManager () {
		return log;
	}
	
	public String getWaitingTime() {
		return time;
	}
	
	public String getResultsTable() {
		return resultsTableElement;
	}
	
	public String getConfigPath() {
		return configPath;
	}
	
	public String getOutputDIR() {
		return outputDIR;
	}
	
	public String getOutputFileName() {
		return outputFileName;
	}
	
	public String getStocklistFile() {
		return stockListFile;
	}
	
	public String getFileHeader() {
		return FileHeader;
	}
	
	public String getTodayDate() {
		date = java.time.LocalDate.now().toString();
		System.out.println(java.time.LocalDate.now());
		return date;
	}
	
	public String getStartDateElement() {
		return startdate;
	}
	
	public String getEndDateElement() {
		return enddate;
	}
	
	public String getToolsDIR() {
		return toolsDIR;
	}
	
	public String getConfigDIR() {
		return configDIR;
	}
	
	public String getLogType() {
		return logType;
	}

	public String getSystemProperty() { return systemProperty; }
}
