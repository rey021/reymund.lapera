package com.planitestexam.uitest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@all",
        glue = {"com.planitestexam.uitest.stepdefs","com.demo.hooks"},
        plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"
        }
)
public class UITestRunner extends AbstractTestNGCucumberTests {
}
