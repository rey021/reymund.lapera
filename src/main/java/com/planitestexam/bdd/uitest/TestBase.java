package com.planitestexam.bdd.uitest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.planitestexam.bdd.uitest.DriverFactory.getChromeDriver;
import static com.planitestexam.bdd.uitest.DriverFactory.getWebDriverWait;

public class TestBase {

    private BrowserActions browserActions = BrowserActions.getInstance();
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    @BeforeClass
    public void startUpBrowser(){
    System.out.println("Before Suite");
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        wait = getWebDriverWait();
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }


    public void launchApplication(String url) {
        logger.info("Open URL: " + url);
        driver.get(url);
        browserActions.waitUntilLoaded(10);

    } //End of goToSite Method
}
