package com.planitestexam.uitest.stepdefs;

import com.demo.implementation.*;
import com.demo.pages.PagePlanitCart;
import com.demo.pages.planittestContactForm;
import com.demo.uitest.BrowserActions;
import com.demo.uitest.BrowserUtils;
import com.demo.uitest.TestBase;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.demo.uitest.DriverFactory.getChromeDriver;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UITestStepDefinition extends TestBase {
    private WebDriver driver = getChromeDriver();
    private planittestContactForm contactForm = new planittestContactForm();
    private Scenario scenario;

    Catalogue catalogue = new Catalogue();
    ShoppingCart cart = new ShoppingCart();


    @Before
    public void setScenario(Scenario scenario) { this.scenario = scenario;}

    @Before
    public void getDriver(){
        driver = getChromeDriver();
    }

    @Given("^Given User is on the (.*)$")
    public void givenUserIsOnTheHttpJupiterCloudPlanittestingCom(String url) {
        driver.get(url);
    }

    @Given("User is navigating the contact page")
    public void guest_is_browsing_the_contact_page_of_http_jupiter_cloud_planittesting_com() throws InterruptedException {
        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-contact\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("/html/body/div[2]/div/form/div/a"));
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
    }

    @And("clicking the submit button")
    public void submitting_the_contact_form() {
        BrowserActions.clickOnceItsClickable(By.xpath("/html/body/div[2]/div/form/div/a"));
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
        BrowserActions.waitTime(5);
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
        BrowserActions.waitTime(5);
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
        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
    }
//
    @Then("verify item has been added to cart")
    public void viewingTheCartMenuVerifyHavingAnd() throws InterruptedException {

        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-cart\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("//*[@id=\"nav-cart\"]/a"));

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());

        boolean elementPresent = BrowserActions.isElementIsPresent(By.xpath("//td[contains(text(),\"Funny Cow\")]"));
        assertThat(elementPresent).isEqualTo(true);
        elementPresent = BrowserActions.isElementIsPresent(By.xpath("//td[contains(text(),\"Fluffy Bunny\")]"));
        assertThat(elementPresent).isEqualTo(true);
    }
//
    @When("adding item and quantity on the ff.")
    public void addingAnd(Map<String, String> dataTable) {

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
        for (Map.Entry<String, String> pair : dataTable.entrySet()) {

            for (int x = Integer.parseInt(pair.getValue()); x > 0; x--) {

                switch (pair.getKey()) {
                    case "Funny Cow":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-6\"]/div/p/a[contains(text(),\"Buy\")]"));
                        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
                        break;
                    case "Fluffy Bunny":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-4\"]/div/p/a"));
                        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
                        break;
                    case "Stuffed Frog":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-2\"]/div/p/a"));
                        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
                        break;
                    case "Valentine Bear":
                        BrowserActions.clickElement(By.xpath("//*[@id=\"product-7\"]/div/p/a"));
                        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
                        break;
                }
            }
        }
    }
//
    @And("adding the following item, and quantity")
    public void pricesForEachItemAreTheFollowing(List<Map<String, String>> dataTable) {

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());

        Map<String, String> itemsMapXpath = new HashMap<>();
        itemsMapXpath.put("Stuffed Frog","//*[@id=\"product-2\"]/div/p/a" );
        itemsMapXpath.put("Fluffy Bunny","//*[@id=\"product-4\"]/div/p/a" );
        itemsMapXpath.put("Valentine Bear","//*[@id=\"product-7\"]/div/p/a" );

        dataTable.stream().forEach(
                map -> {
                    String item = map.get("Item");
                    float price = Float.parseFloat(map.get("Price"));
                    int quantity = Integer.parseInt(map.get("Quantity"));

                    catalogue.setProduct(item,new PhysicalProduct(item, price));

                    System.out.println("PRICE === " + price);
                    Product myItem = Catalogue.getProduct(item);
                    cart.addLineItem(new LineItem(myItem, quantity));

                    for (int x = quantity; x > 0 ; x --){
                        BrowserActions.clickElement(By.xpath(itemsMapXpath.get(item)));
                        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());
                    }

                }
        );
    }
//
    @Then("verify item has been added to cart with correct sub_total of each item and total cost")
    public void verifyItemHasBeenAddedToCartWithTotalCost() throws InterruptedException {

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());

        PagePlanitCart pagePlanitCart = new PagePlanitCart();

        DecimalFormat df = new DecimalFormat("###.#");

        Map<String, String> subTotalMapToXpath = new HashMap<>();
        subTotalMapToXpath.put("subTotalLine1","/html/body/div[2]/div/form/table/tbody/tr[1]/td[4]");
        subTotalMapToXpath.put("subTotalLine2","/html/body/div[2]/div/form/table/tbody/tr[2]/td[4]");
        subTotalMapToXpath.put("subTotalLine3","/html/body/div[2]/div/form/table/tbody/tr[3]/td[4]");


        Utilities utilities = new Utilities();
        BigDecimal totalCostToBigDecimal = utilities.getTotalCostToBigDecimal(cart);

        BrowserActions.clickElement(By.xpath("//*[@id=\"nav-cart\"]/a"));
        BrowserActions.waitUntilElementIsPresent(By.xpath("//*[@id=\"nav-cart\"]/a"));

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());

        boolean result = pagePlanitCart.calculateCart(cart,subTotalMapToXpath);
        System.out.println("Validate subtotal of each lineItem ===  )");

        assertTrue(result);

        System.out.println("Computed Total Cost ===  + " + totalCostToBigDecimal);
        System.out.println("Display on Webpage totalCost == " + BrowserActions.getText(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td")));

        String totalCost = utilities.regex(BrowserActions.getText(By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td")),"(?<=\\s).*");

        BrowserUtils.captureScreenshotToScenario(scenario, BrowserUtils.captureScreenshotOfBrowser());

        if (utilities.checkIfOneDecimal(totalCost)){
            assertEquals(totalCost,df.format(totalCostToBigDecimal));
        }
        else  { assertEquals(totalCost,totalCostToBigDecimal.toString()); }
    }
}//End of class