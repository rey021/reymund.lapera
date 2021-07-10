package com.planitestexam.uitest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.planitestexam.bdd.uitest.execute;
import com.planitestexam.bdd.uitest.getConfig;
import com.planitestexam.bdd.uitest.SeleniumFlow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class UITestStepDefinition {

    execute exe = new execute();
    private SiteCatalog siteCatalog = new SiteCatalog();

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

    @When("Submitting the form leaving the fields NULL")
    public void submitting_the_form_leaving_the_fields_NULL() {
        SE.clickElement("xpath", config.getContactpageSubmitButtonElement());
        //SE.delay(5);
    }

    @Then("he should get the ERROR message")
    public void he_should_get_the_ERROR_message() {
        boolean present = SE.isElementIsPresent("xpath", config.getContactPageErrorMessageElement());
        assertThat(present).isEqualTo(true);
    }
}