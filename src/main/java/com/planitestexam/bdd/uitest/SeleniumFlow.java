package com.planitestexam.bdd.uitest;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
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

	ChromeDriver driver = null;
	
	getConfig config = null;
	LogManager log = null;
	FileHandler fh = new FileHandler();
	static String quote = "";
	String FileHeader="";
	String DateFile;
	String stockListFile;

		
	public void initialized(getConfig config) {
		this.config = config;

		System.setProperty(config.getSystemProperty(), config.getDriverPath() + config.getWebDriver());
		driver= new ChromeDriver();
		stockListFile = config.getConfigPath()+config.getStocklistFile();
	}
		
	public void goToSite(String url) {
	
		this.config = config;
		this.log =config.logManager();
		
		log.info("Open URL: " + url);
		openUrl(url);
		waitUntilLoaded();

	} //End of goToSite Method

	
	private void openUrl(String url) {
		driver.get(url);
	}
	
	private void submit (String id) {
		WebElement element =  driver.findElement(By.xpath(id));
		element.click();
	}
	
	private void switchFrame(String name) {
		if (name != null)
		{
			log.info(" ===> Switching to frame " + name);
			try {
				Thread.sleep(5000);
				driver.switchTo().frame(name);
				waitUntilLoaded();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			log.info(quote + " ===> Switching to default content frame");
			driver.switchTo().defaultContent();
			//log.info("Successfully switched to default content frame");
			waitUntilLoaded();
		}
	}
	
	private void waitUntilLoaded() {

		new WebDriverWait(driver, Long.parseLong(config.getWaitingTime())).until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		log.info("Page load has been complete");
	}
	
	/*private void waitTime(int sec) {
		log.info(quote + " ===> wait for " + sec + " seconds");
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		
	}**/




	
	public void closeDriver() {
		try{
			log.info("Execute Driver quit...");
			driver.quit();
			log.info("Driver Quit --> Done!...");

		}
		catch (Exception e){
			log.error("error in Closedriver Method");
		}
		finally {
			try{
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			}
			catch (Exception e){
				log.error("error in Closedriver --> getruntime.exec");
			}
		}

	} // end of closeDriver method

} // End of SeleniumFlow Class
