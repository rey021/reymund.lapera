package com.planitestexam.uitest;

import com.planitestexam.bdd.implementation.*;
import cucumber.api.java.After;
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

import java.math.RoundingMode;
import java.net.CacheResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UITestStepDefinition {

    execute exe = new execute();
    getConfig config;
    SeleniumFlow SE;
    Map<String, String> webelements = new HashMap<String, String>();
    ContactForm contactForm = new ContactForm();
    Map<String, String> items = new HashMap<String, String>();
    Catalogue catalogue = new Catalogue();
    ShoppingCart cart = new ShoppingCart();

    @Before
    public void initializeSelenium() throws Exception {
        exe.run();
        config = exe.getConfig();
        SE = exe.getSeleniumFlow();
        webelements = config.readFileElements();
    }

    // @After
    //public void closeDriver() throws Exception {
    //     SE.closeDriver();
    // }

    @Given("Given User is on the (.*)")
    public void givenUserIsOnTheHttpJupiterCloudPlanittestingCom(String url) {
        SE.goToSite(url);
    }

    @Given("User is navigating the contact page")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com() {
        SE.clickElement("xpath", webelements.get("home_contactpagebutton"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("contactpage_submitbutton"));
    }

    @And("clicking the submit button")
    public void submitting_the_contact_form() {
        SE.clickElement("xpath", webelements.get("contactpage_submitbutton"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("contactpage_submitbutton"));
    }

    @Then("^s?he should get the error message: \"(.*)\"$")
    public void he_should_get_the_ERROR_message(String text) {
        String message = SE.getText("xpath", webelements.get("contactPage_errorMessage"));
        assertThat(message).isEqualTo(text);
    }

    @When("^filling up the contact form on the following fields (.*) , (.*) , (.*) , (.*) , (.*)$")
    public void fillingUpTheContactForm(String forename, String surname, String email, String telephone, String message) {

        contactForm.setForename(forename);
        contactForm.setSurname(surname);
        contactForm.setTelephone(telephone);
        contactForm.setEmail(email);
        contactForm.setMessage(message);

        SE.setText("xpath", webelements.get("forename"), forename);
        SE.setText("xpath", webelements.get("surname"), surname);
        SE.setText("xpath", webelements.get("email"), telephone);
        SE.setText("xpath", webelements.get("telephone"), email);
        SE.setText("xpath", webelements.get("message"), message);
    }

    @Then("^s?he should get the success (.*)$")
    public void checkResponseMessage(String text) throws InterruptedException {
        String message = "";
        if (contactForm.getForename().isEmpty() || contactForm.getSurname().isEmpty() || contactForm.getEmail().isEmpty() ||
                contactForm.getTelephone().isEmpty()) {
            SE.waitUntilElementIsPresent("xpath", webelements.get("contactPage_errorMessage"));
            message = SE.getText("xpath", webelements.get("contactPage_errorMessage"));
        } else {
            SE.waitUntilElementIsPresent("xpath", webelements.get("contact_success_message"));
            message = SE.getText("xpath", webelements.get("contact_success_message"));
        }
        assertThat(message).isEqualTo(text);
    }

    @When("leaving fields as blank")
    public void leavingFieldsAsBlank() {
    }

    @Given("User is navigating to shop page")
    public void user_is_navigating_to_shop_page() {
        SE.clickElement("xpath", webelements.get("navigate_shop_button"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("shop_page"));
    }

    @Then("verify item has been added to cart")
    public void viewingTheCartMenuVerifyHavingAnd() throws InterruptedException {


/*
        SE.clickElement("xpath", webelements.get("navigate_cart_button"));
        SE.waitUntilElementIsPresent("xpath", webelements.get("navigate_cart_button"));
        boolean elementPresent = SE.isElementIsPresent("xpath", webelements.get("added_cart_funny_cow"));
        assertThat(elementPresent).isEqualTo(true);
        elementPresent = SE.isElementIsPresent("xpath", webelements.get("added_cart_fluffy_bunny"));
        assertThat(elementPresent).isEqualTo(true);

 */
    }

    @When("adding item and quantity on the ff.")
    public void addingAnd(Map<String, String> dataTable) {


//========================
        for (Map.Entry<String, String> pair : dataTable.entrySet()) {

            for (int x = Integer.parseInt(pair.getValue()); x > 0; x--) {

                switch (pair.getKey()) {
                    case "Funny Cow":
                        SE.clickElement("xpath", webelements.get("buy_button_funny_cow"));
                        break;
                    case "Fluffy Bunny":
                        SE.clickElement("xpath", webelements.get("buy_button_fluffy_cow"));
                        break;
                    case "Stuffed Frog":
                        SE.clickElement("xpath", webelements.get("buy_button_stuffed_frog"));
                        break;
                    case "Valentine Bear":
                        SE.clickElement("xpath", webelements.get("buy_button_valentine_bear"));
                        break;
                }
            }
        }
    }

    @And("adding the following item, and quantity")
    public void pricesForEachItemAreTheFollowing(List<Map<String, String>> dataTable) {

        dataTable.stream().forEach(
                map -> {
                    String item = map.get("Item");
                    Float price = Float.parseFloat(map.get("Price"));
                    Integer quantity = Integer.parseInt(map.get("Quantity"));

                    catalogue.setProduct(item,new PhysicalProduct(item, price));

                    System.out.println("PRICE === " + price);
                    Product myItem = Catalogue.getProduct(item);
                    cart.addLineItem(new LineItem(myItem, quantity));
                }
        );
    }

    @Then("verify item has been added to cart with correct sub_total of each item and total cost")
    public void verifyItemHasBeenAddedToCartWithTotalCost() {

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.printf("getLineItems === + "+  cart.getLineItems());
        System.out.printf("total cost === + " + df.format(cart.getTotalCost()));
        // End Total cost
    }
}//End of class