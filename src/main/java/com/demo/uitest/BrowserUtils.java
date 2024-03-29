package com.demo.uitest;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;


import static com.demo.uitest.DriverFactory.getChromeDriver;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

public class BrowserUtils {

    private static WebDriver driver = getChromeDriver();
    private static BrowserManager browserManager = BrowserManager.getInstance();

    private static final byte[] EMPTY_SCREENSHOT_ARRAY = new byte[0];

    public static byte[] captureScreenshotOfBrowser(){
        failIfCaptureScreenshotIsDisable();
//        if (isWebDriverHtmlUnit()) {
//            return EMPTY_SCREENSHOT_ARRAY;
//        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

//    private static boolean isWebDriverHtmlUnit() {
//       // return SeleniumFlow.getInstance().getWebDriver() instanceof HtmlUnitDriver;
//    }
    public static void captureScreenshotToScenario(Scenario scenarioToAttach, byte[] imagesBytesToAttach){
        if (isNotEmpty(imagesBytesToAttach)){
            scenarioToAttach.attach(imagesBytesToAttach, "image/png", "screenshot");
        }
    }

    private static void failIfCaptureScreenshotIsDisable() {
        if (captureScreenshotDisabled()){
            throw new IllegalStateException("CaptureScreenshot is disabled at Framework layer.");
        }
    }

    private static boolean captureScreenshotDisabled() {
        return !browserManager.getScreenshotCaptureStatus();
    }

}
