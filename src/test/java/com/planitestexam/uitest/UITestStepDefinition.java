package com.planitestexam.uitest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.planitestexam.bdd.uitest.execute;
import com.planitestexam.bdd.uitest.getConfig;
import com.planitestexam.bdd.uitest.SeleniumFlow;
import io.cucumber.datatable.DataTable;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

public class UITestStepDefinition {

    execute exe = new execute();
    getConfig config;
    SeleniumFlow SE;
    Map<String, String> webelements = new HashMap<String, String>();

    @Before
    public void initializeSelenium() throws Exception {
        exe.run();
        config = exe.getConfig();
        SE = exe.getSeleniumFlow();
        webelements = config.readFileElements();
    }

    @Given("Given User is on the (.*)")
    public void givenUserIsOnTheHttpJupiterCloudPlanittestingCom(String url) {
        SE.goToSite(url);
    }

    @Given("User is navigating the contact page")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com() {
        SE.clickElement("xpath",webelements.get("home_contactpagebutton"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("contactpage_submitbutton"));
    }

    @When("clicking the submit button")
    public void submitting_the_contact_form() {
        SE.clickElement("xpath", webelements.get("contactpage_submitbutton"));
        SE.delay(20);
    }

    @Then("^s?he should get the error message: \"(.*)\"$")
    public void he_should_get_the_ERROR_message(String text) {
        String message = SE.getText("xpath",webelements.get("contactPage_errorMessage"));
        assertThat(message).isEqualTo(text);
    }

    @When("filling up the contact form on the following fields:")
    public void fillingUpTheContactForm(DataTable table) {
        SE.setText("xpath",webelements.get("forename"),table.cell(1, 0));
        SE.setText("xpath",webelements.get("surname"),table.cell(1, 1));
        SE.setText("xpath",webelements.get("email"),table.cell(1, 2));
        SE.setText("xpath",webelements.get("telephone"),table.cell(1, 3));
        SE.setText("xpath",webelements.get("message"),table.cell(1, 4));
    }

    @Then("^s?he should get the not get error message any (.*)$")
    public void heShouldGetTheNotGetErrorMessageAnyERRORMESSAGE(String text) {
        SE.waitUntilElementIsPresent("xpath", webelements.get("contact_success_message"));
        String message = SE.getText("xpath",webelements.get("contact_success_message"));
        assertThat(message).isEqualTo(text);
    }

    @When("leaving fields as blank")
    public void leavingFieldsAsBlank() {
    }
}