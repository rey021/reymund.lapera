package com.planitestexam.uitest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.planitestexam.bdd.uitest.execute;
import com.planitestexam.bdd.uitest.getConfig;
import com.planitestexam.bdd.uitest.SeleniumFlow;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class UITestStepDefinition {

    execute exe = new execute();
    getConfig config;
    SeleniumFlow SE;

    @Before
    public void initializeSelenium() throws Exception {
        exe.run();
        config = exe.getConfig();
        SE = exe.getSeleniumFlow();
    }

    @Given("User is browsing the contact page of (.*)")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com(String url) {
        SE.goToSite(url);
        SE.clickElement("xpath",config.getContactPageElement());
        SE.waitUntilElementIsPresent("xpath", config.getContactpageSubmitButtonElement());
    }

    @When("Submitting the form leaving the fields as null")
    public void submitting_the_form_leaving_the_fields_NULL() {
        SE.clickElement("xpath", config.getContactpageSubmitButtonElement());
        //SE.delay(5);
    }

    @Then("^s?he should get the error message as (.*)$")
    public void he_should_get_the_ERROR_message(String text) {
        boolean present = SE.isElementIsPresent("xpath", config.getContactPageErrorMessageElement());
        System.out.println(Boolean.valueOf(text));
        System.out.println(present);
        assertThat(present).isEqualTo(Boolean.valueOf(text));
    }

    @When("filling up the contact form")
    public void fillingUpTheContactForm() {
        
    }

    @And("clicking the submit button")
    public void clickingTheSubmitButton() {
        
    }

    @Then("^s?he should get the not get error message any (.*)$")
    public void heShouldGetTheNotGetErrorMessageAnyERRORMESSAGE() {
    }
}