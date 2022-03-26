package com.planitestexam.bdd.uitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory(){
        // prevent instantiation
    }

    public static WebDriver getChromeDriver(){
        if(driver == null) {
            synchronized(BrowserActions.class) {
                if(driver == null) {
                    try {
                        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
                        driver = new ChromeDriver();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait(){
        if(wait == null){
            wait = new WebDriverWait(getChromeDriver(), 5);
        }
        return wait;
    }
}