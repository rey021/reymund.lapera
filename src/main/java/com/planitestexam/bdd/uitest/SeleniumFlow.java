package com.planitestexam.bdd.uitest;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



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
		waitTime();

	} //End of goToSite Method
	
	public void goToBrokerHist() throws InterruptedException {
		clickQuote();
		clickBrokerHist();
	} //End of goToBrokerHist method
	
	
	/*private getAllStocks() {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(configPath + "configuration.txt");
		prop.load(input);
		log.info();
	}
	*/
	
	private void inputQuote (String stock) throws InterruptedException {
		
		WebElement txt = null;
		waitTime();
		
		switchFrame(null);
		switchFrame("main");
		
		switchFrame("brokertrxnin2");

		txt = driver.findElement(By.id("ebrokerno"));
		Thread.sleep(3000);
		log.info(stock + " === > putting qoute value");
		txt.sendKeys(stock);
		txt.submit();
		Thread.sleep(8000);
		txt.submit();
		
		log.info("submitting input ===> " + quote);	
		Thread.sleep(8000);
	}
	
	private void openUrl(String url) {
		driver.get(url);
	}
	
	private void setUser1Name(String id) {
		WebElement element = driver.findElement(By.name(id));
		element.sendKeys(config.username1);
	}
	
	private void setUser2Name(String id) {
		WebElement element = driver.findElement(By.name(id));
		element.sendKeys(config.username2);
	}
	
	private void setPass (String id) {
		WebElement element = driver.findElement(By.name(id));
		element.sendKeys(config.password);
	}
	
	private void submit (String id) {
		WebElement element =  driver.findElement(By.xpath(id));
		element.click();
	}
	
	private void clickQuote() throws InterruptedException {
				
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switchFrame("headern");
		waitTime();
		
		log.info("click Quote");
		WebElement txt = driver.findElement(By.cssSelector("#CQ"));
		txt.click();
		log.info("Successfully click quote");
		
		waitTime();
	}
	
	private void clickBrokerHist() {
		WebElement txt = driver.findElement(By.cssSelector("#L1_2_3"));
		txt.click();
	}	
	
	private void switchFrame(String name) {
		if (name != null)
		{
			log.info(quote + " ===> Switching to frame " + name);
			try {
				Thread.sleep(5000);
				driver.switchTo().frame(name);
				waitTime();
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
			waitTime();
		}
	}
	
	private void waitTime() {
		log.info(quote + " ===> wait for " + config.getWaitingTime() + " seconds");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getWaitingTime()), TimeUnit.SECONDS);
	}
	
	/*private void waitTime(int sec) {
		log.info(quote + " ===> wait for " + sec + " seconds");
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		
	}**/
	
	private void fetchResults() {
		
		switchFrame(null);
		switchFrame("main");
		//log.info("check if frame is visible === " + driver.findElement(By.name("brokertrxnout2")).getAttribute("name"));		
		switchFrame("brokertrxnout2");
		
		
		List <WebElement> rows = driver.findElements(By.cssSelector(".sortable > tbody:nth-child(2) tr")); 
		log.info(quote + " ===> size of row == " + rows.size());	
		
		
		
		for (int count = 1 ; count <= rows.size() && rows.size() != 0; count++ ) 
			{
			String[] index = new String[20];
			log.info("count is ===> " + count);
			StringBuilder stringBuilder = new StringBuilder();
			
			for (int y = 2; y < 12; y++)
				{
				log.info("Y is ===> " + y);
				
					index[y] = driver.findElement(By.xpath(config.getResultsTable() + "tr[" + count + "]/td[" + y + "]")).getText().replaceAll(",","");
					
					if ( y == 2)
					{
						stringBuilder.append(index[y]).toString();
					}
					else {
						stringBuilder.append("," + index[y].toString());
					}
				} // end of inner for loop
			
			try {
				stringBuilder.append("," + DateFile + "," + quote);
				log.info(quote + " ===> " + stringBuilder.toString());
				fh.addLine(stringBuilder.toString());
			}
			
			catch (Exception e) {
				log.error(quote + " ===> unable to write output file");
			}
			
		} // end of outer for loop	
		rows.clear();
	} // End of fetchResults method
	
	public void getAllListOfStocks( String pastdays) throws Exception {
		
		log.info("Printing pastdays == " + pastdays);
		int days = Integer.valueOf(pastdays);
		log.info("Printing days == " + days);

		selectDate(days);

		String temporaryFileName=config.getOutputDIR() + config.getStocklistFile() + "_" + DateFile;

		fh.setFileName(stockListFile);
		Vector vec = fh.readTheFile();
        Enumeration en = vec.elements();

        log.info("setting file " + temporaryFileName);
        fh.setFileName(temporaryFileName + ".csv");
        
        if(fh.isExist() == true) {
        	log.info("File: " + fh.fileName + " " + "Already Exist");
        }
        
        else {
			fh.setFileName(temporaryFileName);
    		log.info(quote + " ===>  file " + fh.fileName + " not yet created.. fetching results ");
    		
    		fh.addLine(config.getFileHeader());
    		log.info("Added Header line ==> " + config.getFileHeader());

            while (en.hasMoreElements()) {
            	SeleniumFlow.quote = en.nextElement().toString();
            	
            	inputQuote(quote);
            	        	
                fetchResults();
            	
            	log.info("Done fetching === > " + quote);
            	try {
    				Thread.sleep(3000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            } // End of while loop
			fh.renameFile(temporaryFileName, temporaryFileName + ".csv");
			log.info("renamed file to " +temporaryFileName + ".csv" );
        }
       
	} //End of getAllListOfStocks method
	
	public void getSpecificStock(String quote) {
		try {
			inputQuote(quote);
			fetchResults();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void selectDate(int pastdays) {
		switchFrame(null);		
		switchFrame("main");
		switchFrame("brokertrxnin2");
		Select drpDate = new Select (driver.findElement(By.name(config.getStartDateElement())));
		
		drpDate.selectByIndex(pastdays);
		
		drpDate = new Select (driver.findElement(By.name(config.getEndDateElement())));
		drpDate.selectByIndex(pastdays);
		
		DateFile = driver.findElement(By.name(config.getStartDateElement())).getAttribute("value").replace("/", "-");
	}
	
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

	public void getAllListOfStocks(String[] args) throws Exception {
		for (String i : args ) {
			getAllListOfStocks(i);
		} // end of for loop
	} // end of getAllListOfStocks method
} // End of SeleniumFlow Class
