package com.planitestexam.uitest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/UITest",
        glue = "com.planitestexam.uitest",
        plugin = { "pretty", "html:target/html-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        tags = { "@testcase4" }
)
public class UITest_Runner {
}
