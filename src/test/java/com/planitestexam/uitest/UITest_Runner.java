package com.planitestexam.uitest;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "classpath:features/uitest",
        glue = {"com.planitestexam.uitest","com.planitestexam.bdd.hooks"},
        plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"
        },
        tags = {"@testcase1&2"}

)
public class UITest_Runner extends AbstractTestNGCucumberTests {

}
