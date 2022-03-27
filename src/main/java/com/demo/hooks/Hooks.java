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

    private static WebDriver driver = getChromeDriver();

    @BeforeAll
    public static void before_all(){
        System.out.println("Before suite\n get driver");
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterAll
    public static void after_all(){
        System.out.println("AfterSuite.\nquit driver");
        driver.close();
        driver.quit();
    }
}
