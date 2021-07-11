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

    @And("clicking the submit button")
    public void submitting_the_contact_form() {
        SE.clickElement("xpath", webelements.get("contactpage_submitbutton"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("contactpage_submitbutton"));
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

    @Then("^s?he should get the success (.*)$")
    public void heShouldGetTheNotGetErrorMessageAnyERRORMESSAGE(String text) throws InterruptedException {
        SE.waitUntilElementIsPresent("xpath", webelements.get("contact_success_message"));
        String message = SE.getText("xpath",webelements.get("contact_success_message"));
        assertThat(message).isEqualTo(text);
    }

    @When("leaving fields as blank")
    public void leavingFieldsAsBlank() {
    }

    @Given("User is navigating to shop page")
    public void user_is_navigating_to_shop_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("adding the following item to cart Funny Cow <QUANTITY>")
    public void adding_the_following_item_to_cart_Funny_Cow_QUANTITY(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    @When("clicking the cart menu")
    public void clicking_the_cart_menu() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("he should able to validate the correct item into the cart page")
    public void he_should_able_to_validate_the_correct_item_into_the_cart_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("adding the following item to cart Fluffy Bunny < QUANTITY>")
    public void adding_the_following_item_to_cart_Fluffy_Bunny_QUANTITY(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }
}