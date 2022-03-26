package com.planitestexam.uitest;

import com.planitestexam.bdd.implementation.*;
import com.planitestexam.bdd.implementation.ContactForm;
import com.planitestexam.bdd.pages.planittestContactForm;
import com.planitestexam.bdd.uitest.BrowserActions;
import com.planitestexam.bdd.uitest.BrowserUtils;
import com.planitestexam.bdd.uitest.execute;
import com.planitestexam.bdd.uitest.SeleniumFlow;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UITestStepDefinition {

    private planittestContactForm contactForm = new planittestContactForm();
    private Scenario scenario;

    SeleniumFlow SE = SeleniumFlow.getInstance();
    Map<String, String> webelements = new HashMap<String, String>();

    Map<String, String> items = new HashMap<String, String>();
    Catalogue catalogue = new Catalogue();
    ShoppingCart cart = new ShoppingCart();


    @Before
    public void setScenario(Scenario scenario) { this.scenario = scenario;}

    @Given("^Given User is on the (.*)$")
    public void givenUserIsOnTheHttpJupiterCloudPlanittestingCom(String url) {
        System.out.println("ASDASDASDASD");
        SE.goToSite(url);
    }

    @Given("User is navigating the contact page")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com() throws InterruptedException {
        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-contact\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("/html/body/div[2]/div/form/div/a"));
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
    }

    @And("clicking the submit button")
    public void submitting_the_contact_form() throws InterruptedException {
        BrowserActions.clickElement(By.xpath("/html/body/div[2]/div/form/div/a"));
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
        BrowserActions.waitUntilElementIsPresent(By.xpath("/html/body/div[2]/div/form/div/a"));
//        SE.waitUntilElementIsPresent("xpath", webelements.get("contactpage_submitbutton"));
    }
//
//    @Then("^s?he should get the error message: \"(.*)\"$")
//    public void he_should_get_the_ERROR_message(String text) {
//        String message = SE.getText("xpath", webelements.get("contactPage_errorMessage"));
//        assertThat(message).isEqualTo(text);
//    }
//
    @When("^filling up the contact form on the following fields (.*) , (.*) , (.*) , (.*) , (.*)$")
    public void fillingUpTheContactForm(String forename, String surname, String email, String telephone, String message) {
        contactForm.fillUpForm(forename,surname,email,telephone,message);
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
    }
//
    @Then("^s?he should get the the following (.*)$")
    public void checkResponseMessage(String expectedResult) throws InterruptedException {
        contactForm.validate(expectedResult);
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
    }
//
//    @When("leaving fields as blank")
//    public void leavingFieldsAsBlank() {
//    }
//
    @Given("User is navigating to shop page")
    public void user_is_navigating_to_shop_page() throws InterruptedException {
        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-shop\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("/html/body/div[2]/div[@class=\"products ng-scope\"]"));
//        SE.clickElement("xpath", webelements.get("navigate_shop_button"));
//        SE.waitUntilElementIsPresent("xpath", webelements.get("shop_page"));
    }
//
    @Then("verify item has been added to cart")
    public void viewingTheCartMenuVerifyHavingAnd() throws InterruptedException {

        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-cart\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("//*[@id=\"nav-cart\"]/a"));
        boolean elementPresent = BrowserActions.isElementIsPresent(By.xpath("//td[contains(text(),\"Funny Cow\")]"));
        assertThat(elementPresent).isEqualTo(true);
        elementPresent = BrowserActions.isElementIsPresent(By.xpath("//td[contains(text(),\"Fluffy Bunny\")]"));
        assertThat(elementPresent).isEqualTo(true);
    }
//
    @When("adding item and quantity on the ff.")
    public void addingAnd(Map<String, String> dataTable) {

        for (Map.Entry<String, String> pair : dataTable.entrySet()) {

            for (int x = Integer.parseInt(pair.getValue()); x > 0; x--) {

                switch (pair.getKey()) {
                    case "Funny Cow":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-6\"]/div/p/a[contains(text(),\"Buy\")]"));
                        break;
                    case "Fluffy Bunny":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-4\"]/div/p/a"));
                        break;
                    case "Stuffed Frog":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-2\"]/div/p/a"));
                        break;
                    case "Valentine Bear":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-7\"]/div/p/a"));
                        break;
                }
            }
        }
    }
//
//    @And("adding the following item, and quantity")
//    public void pricesForEachItemAreTheFollowing(List<Map<String, String>> dataTable) {
//
//        dataTable.stream().forEach(
//                map -> {
//                    String item = map.get("Item");
//                    Float price = Float.parseFloat(map.get("Price"));
//                    Integer quantity = Integer.parseInt(map.get("Quantity"));
//
//                    catalogue.setProduct(item,new PhysicalProduct(item, price));
//
//                    System.out.println("PRICE === " + price);
//                    Product myItem = Catalogue.getProduct(item);
//                    cart.addLineItem(new LineItem(myItem, quantity));
//
//                    for (int x = quantity; x > 0 ; x --){
//                        SE.clickElement("xpath", webelements.get(item));
//                    }
//
//                }
//        );
//    }
//
//    @Then("verify item has been added to cart with correct sub_total of each item and total cost")
//    public void verifyItemHasBeenAddedToCartWithTotalCost() throws InterruptedException {
//
//        DecimalFormat df = new DecimalFormat("###.#");
//        BigDecimal totalCostToBigDecimal = new BigDecimal(Double.toString(cart.getTotalCost()));
//        totalCostToBigDecimal = totalCostToBigDecimal.setScale(2, RoundingMode.HALF_UP);
//
//        SE.clickElement("xpath", webelements.get("navigate_cart_button"));
//        SE.waitUntilElementIsPresent("xpath", webelements.get("navigate_cart_button"));
//
//        int count = 1;
//        cart.getLineItems().stream().forEach(
//                items -> {
//
//                    Product item = items.getProduct();
//                    int counter = 1 ;
//                    for (int x = counter; x < cart.getLineItems().size()+1; x++){
//                        String displayItem = SE.getText("xpath", "/html/body/div[2]/div/form/table/tbody/tr["
//                                + counter + "]/td[1]");
//                        if (displayItem.equals(item.getName())){
//
//                            String subTotalDisplay= SE.getText("xpath",webelements.get("subTotalLine" + count));
//
//                            BigDecimal subTotalToBigDecimal = new BigDecimal(Double.toString(items.getPrice()));
//                            subTotalToBigDecimal = subTotalToBigDecimal.setScale(2, RoundingMode.HALF_UP);
//
//                            assertEquals(subTotalDisplay,"$"+subTotalToBigDecimal); // Validate subtotal of each lineItem
//
//                            break;
//                        }
//                    }
//                }
//        );
//
//        System.out.println("Computed Total Cost ===  + " + totalCostToBigDecimal);
//        System.out.println("Display on Webpage totalCost == " + SE.getText("xpath",webelements.get("totalPrice")));
//
//        String totalCost = regex(SE.getText("xpath",webelements.get("totalPrice")),"(?<=\\s).*");
//
//        if (checkIfOneDecimal(totalCost)){
//            assertEquals(totalCost,df.format(totalCostToBigDecimal));
//        }
//        else
//        {
//            assertEquals(totalCost,totalCostToBigDecimal.toString());
//        }
//    }
//
//    private boolean checkIfOneDecimal(String totalCost) {
//
//        boolean status = false;
//        int indexOfDecimal = totalCost.indexOf(".")+1;
//
//        if(((totalCost.length() - indexOfDecimal)) == 1) {
//            status = true;
//        }
//        return status;
//    }
//
//    private String regex(String value, String regex)
//    {
//        Pattern p = Pattern.compile(regex);//. represents single character
//        Matcher m = p.matcher(value);
//        String result = "";
//
//        if (m.find()){
//            return m.group(0);
//        }
//        return null;
//    } // End of method regex

}//End of class