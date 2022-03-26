package com.planitestexam.bdd.uitest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.planitestexam.bdd.uitest.DriverFactory.getChromeDriver;

public class BrowserActions {

    private static final WebDriver driver = getChromeDriver();

    private static BrowserActions instance = null;

    private static final Logger logger = LogManager.getLogger(BrowserActions.class);

    private BrowserActions(){
        if(instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static BrowserActions getInstance() {
        if(instance == null) {
            synchronized(BrowserActions.class) {
                if(instance == null) {
                    try {
                        instance = new BrowserActions();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
            }
        }
        return instance;
    }
    public static <T> void clickElement(By locator) {
        logger.info("Clicking the element... Locator: " + locator + " === " + "Element: ");
        WebElement element =  driver.findElement(locator);
        element.click();
       // waitUntilLoaded();
        logger.info("Successfully clicked the element");
    }

    public static WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        return element;
    }

    public static void waitUntilLoaded( int timeoOutiInSecond) {

        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        logger.info("Page load has been complete");
    }

    public static void waitTime(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static <T> WebElement getElement(T locator) {
        String classLocator = getLocatorClass(locator);
        WebElement ele = driver.findElement(By.xpath(locator.toString()));
        System.out.println("pumasok??");

        return ele;
    }

    protected static <T> String getLocatorClass(T locator) {
        return locator.getClass().getSimpleName().replaceFirst("y(.*)", "y");
    }

    public static FluentWait<WebDriver> createFluentWait(List<Class<? extends Throwable>> classExceptionToIgnore) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoreAll(classExceptionToIgnore);
    }

    public static void highLighterMethod(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
    public static <T> By getBy(T locatorByOrElement) {
        String classLocator = getLocatorClass(locatorByOrElement);

        switch (classLocator){
            case "By": {
                return ((By) locatorByOrElement);
            }
            case "WebElement":
            case "RemoteWebElement":
            case "EventFiringWebElement":
            case "HtmlUnitWebElement": {
                return getBy(locatorByOrElement.toString().split(" -> ")[1].replace("]", "").split(": "));
            }
            default:
                throw new IllegalArgumentException(String.format("Only 'By' and 'WebElement' locators are allowed."));
        }
    }

    public static void clickOnceItsClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }


    public enum Status {
        PASS,
        FAIL,
        FOCUS_WARNING
    }

    public static void waitUntilElementIsPresent(By locator ) throws InterruptedException {
        logger.info("Locator: " + locator.toString() + " === " + "Element: ");
        WebDriverWait w = new WebDriverWait(driver,10);
        // presenceOfElementLocated condition
        w.until(ExpectedConditions.presenceOfElementLocated (locator));
        //logger.info("Element present having text:" + findBy(locator, id).toString());
        Thread.sleep(3000);
    }

    private static By findBy(String locator, String id) {
        By by = null;
        if (locator.equalsIgnoreCase("xpath")){
            by = By.xpath(id);
        }
        return by;
    }

    public boolean isElementIsPresent(String locator, String id) throws InterruptedException {
        Thread.sleep(7000);
        return driver.findElement(findBy(locator, id)).isDisplayed();
    }

    	public static void setText(By locator, String value){
		logger.info("ACTION: Set text, locator: " + locator + " ID: " + " value: " + value);
		WebElement element =  findElement(locator);
		element.sendKeys(value);
		logger.info("SETTEXT has been executed");
	}

    public static boolean isElementIsPresent(By locator) throws InterruptedException {
        Thread.sleep(7000);
        return driver.findElement(locator).isDisplayed();
    }

    public static String getText(By locator){
        String text = findElement(locator).getText();
        logger.info("GETTEXT Value = " + text);
        return text;
    }
}
