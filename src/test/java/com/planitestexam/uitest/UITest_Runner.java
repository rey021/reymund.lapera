package com.planitestexam.uitest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/UITest",
        glue = "com.planitestexam.uitest"
)
public class UITest_Runner {
}
