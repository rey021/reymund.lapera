package com.planitestexam.uitest;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "classpath:features/uitest",
        glue = {"com.planitestexam.uitest","com.planitestexam.bdd.hooks"},
        plugin = { "pretty", "html:target/html-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        tags = {"@testcase1&2"}

)
public class UITest_Runner extends AbstractTestNGCucumberTests {

}
