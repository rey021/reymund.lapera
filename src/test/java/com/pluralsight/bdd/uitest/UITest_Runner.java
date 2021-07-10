package com.pluralsight.bdd.uitest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/UITest",
        glue = "com.pluralsight.bdd.uitest"
)
public class UITest_Runner {
}
