package com.planitestexam.bdd.hooks;


import com.planitestexam.bdd.uitest.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;


import static com.planitestexam.bdd.uitest.DriverFactory.getChromeDriver;

public class Hooks extends TestBase{
    private WebDriver driver = getChromeDriver();

    public Hooks() {
    }

}
