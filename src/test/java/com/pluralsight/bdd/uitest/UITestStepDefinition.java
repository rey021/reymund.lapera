package com.pluralsight.bdd.uitest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UITestStepDefinition {

    @Given("the URL to test is:")
    public void the_URL_to_test_is(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    @Given("guest is browsing the contact page of http:\\/\\/jupiter.cloud.planittesting.com")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("Submitting the form leaving the fields NULL")
    public void submitting_the_form_leaving_the_fields_NULL() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("he should get the ERROR message")
    public void he_should_get_the_ERROR_message() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }



    Process finished with exit code 0

}
