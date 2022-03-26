package com.planitestexam.bdd.uitest;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import org.openqa.selenium.support.ui.WebDriverWait;
import tools.FileHandler;

import static java.lang.Runtime.getRuntime;

public class SeleniumFlow {

	private static volatile SeleniumFlow instance = null;
	private static volatile GetConfig config = GetConfig.getInstance();
	private static final Logger logger = LogManager.getLogger(SeleniumFlow.class);
	private static BrowserActions browserActions = BrowserActions.getInstance();

	ChromeDriver driver = null;

	private SeleniumFlow(){
		if(instance != null) {
			throw new RuntimeException("Use getInstance() method to create");
		}
	}

	public static SeleniumFlow getInstance() {
		if(instance == null) {
			synchronized(SeleniumFlow.class) {
				if(instance == null) {
					instance = new SeleniumFlow();
					instance.initialized();
				}
			}
		}
		return instance;
	}

	public void initialized() {

		System.setProperty(config.getSystemProperty(), config.getDriverPath() + config.getWebDriver());
		driver= new ChromeDriver();
	}
		
	public void goToSite(String url) {
		logger.info("Open URL: " + url);
		openUrl(url);
		browserActions.waitUntilLoaded();

	} //End of goToSite Method

	
	private void openUrl(String url) {
		driver.get(url);
	}
	
	private void submit (String id) {
		WebElement element =  driver.findElement(By.xpath(id));
		element.click();
	}


//	public void clickElement(String locator, String id) {
//		logger.info("Clicking the element... Locator: " + locator + " === " + "Element: " + id );
//		findElement(locator, id);
//		WebElement element =  findElement(locator, id);
//		element.click();
//		waitUntilLoaded();
//		logger.info("Successfully clicked the element");
//	}
//	private void switchFrame(String name) {
//		if (name != null)
//		{
//			logger.info(" ===> Switching to frame " + name);
//			try {
//				Thread.sleep(5000);
//				driver.switchTo().frame(name);
//				waitUntilLoaded();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	}
	



	
	/*private void waitTime(int sec) {
		log.info(quote + " ===> wait for " + sec + " seconds");
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		
	}**/




	
	public void closeDriver() {
		try{
			logger.info("Execute Driver quit...");
			driver.quit();
			logger.info("Driver Quit --> Done!...");

		}
		catch (Exception e){
			logger.error("error in Closedriver Method");
		}
		finally {
			try{
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			}
			catch (Exception e){
				logger.error("error in Closedriver --> getruntime.exec");
			}
		}

	} // end of closeDriver method












//


	public void delay() throws InterruptedException {
		logger.info("start wait");
		Thread.sleep(20000);
		logger.info("end wait");
	}

	public WebDriver getWebDriver(){
		return driver;
	}


} // End of SeleniumFlow Class
