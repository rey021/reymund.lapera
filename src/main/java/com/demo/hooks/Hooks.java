package com.demo.hooks;

import com.demo.uitest.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.demo.uitest.DriverFactory.getChromeDriver;

public class Hooks extends TestBase{

    private WebDriver driver = getChromeDriver();

    @BeforeAll
    public void startUpBrowser(){
        System.out.println("Before suite\n get driver");
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterAll
    public void tearDown(){
        System.out.println("AfterSuite.\nquit driver");
        driver.quit();
    }

    @After
    public void closeBrowser(){
        System.out.println("AfterTest.\nclose browser");
        driver.close();
    }
}
