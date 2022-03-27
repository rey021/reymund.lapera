package com.demo.uitest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static com.demo.uitest.DriverFactory.getChromeDriver;
import static com.demo.uitest.DriverFactory.getWebDriverWait;

public class TestBase {

    private BrowserActions browserActions = BrowserActions.getInstance();
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(TestBase.class);



    @AfterSuite(alwaysRun = true)
    public void closeBrowser(){
        System.out.println("AfterSuite");
        driver.close();
    }


    public void launchApplication(String url) {
        logger.info("Open URL: " + url);
        driver.get(url);
        browserActions.waitUntilLoaded(10);

    } //End of goToSite Method
}
