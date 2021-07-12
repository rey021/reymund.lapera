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

	ChromeDriver driver = null;
	
	getConfig config = null;
	LogManager log = null;

		
	public void initialized(getConfig config) {
		this.config = config;

		System.setProperty(config.getSystemProperty(), config.getDriverPath() + config.getWebDriver());
		driver= new ChromeDriver();
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

	public void waitUntilElementIsPresent(String locator, String id ) throws InterruptedException {
		log.info("Locator: " + locator + " === " + "Element: " + id );
		WebDriverWait w = new WebDriverWait(driver,Long.parseLong(config.getWaitingTime()));
		// presenceOfElementLocated condition
		w.until(ExpectedConditions.presenceOfElementLocated (findBy(locator, id)));
		log.info("Element present having text:" + findBy(locator, id).toString());
		Thread.sleep(3000);
	}

	private By findBy(String locator, String id) {
		By by = null;
		if (locator.toLowerCase() == "xpath"){
			by = By.xpath(id);
		}
		return by;
	}

	public void clickElement(String locator, String id) {
		log.info("Clicking the element... Locator: " + locator + " === " + "Element: " + id );
		findElement(locator, id);
		WebElement element =  findElement(locator, id);
		element.click();
		waitUntilLoaded();
		log.info("Successfully clicked the element");
	}

	private WebElement findElement(String locator, String id) {
		WebElement element = null;
		if (locator.toLowerCase() == "xpath"){
			element = driver.findElement(By.xpath(id));
		}
		return element;
	}

	public boolean isElementIsPresent(String locator, String id) throws InterruptedException {
		Thread.sleep(7000);
		return driver.findElement(findBy(locator, id)).isDisplayed();
	}

	public String getText(String locator, String id){
		log.info("ACTION: GETTEXT, LOCATOR: " + locator + " ID: " + id);
		String text = findElement(locator, id).getText();
		log.info("GETTEXT Value = " + text);
		return text;
	}

	public void setText(String locator, String id, String value){
		log.info("ACTION: Set text, locator: " + locator + " ID: " + id + " value: " + value);
		WebElement element =  findElement(locator, id);
		element.sendKeys(value);
		log.info("SETTEXT has been executed");
	}

	public void delay() throws InterruptedException {
		log.info("start wait");
		Thread.sleep(20000);
		log.info("end wait");
	}


} // End of SeleniumFlow Class
