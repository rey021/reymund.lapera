package com.planitestexam.uitest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@all",
        glue = {"com.planitestexam.uitest","com.planitestexam.bdd.hooks"},
        plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"
        }
)
public class UITest_Runner extends AbstractTestNGCucumberTests {

}
