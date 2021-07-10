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
                "junit,target/cucumber.xml",
                "rerun:target/rerun.txt"
        }
)
public class UITest_Runner {
}
