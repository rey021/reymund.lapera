package com.demo.pages;

import com.demo.implementation.ContactForm;
import com.demo.uitest.BrowserActions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.demo.uitest.BrowserActions.findElement;
import static org.assertj.core.api.Assertions.assertThat;

public class planittestContactForm {
    private static BrowserActions browserActions = BrowserActions.getInstance();
    private static final Logger logger = LogManager.getLogger(planittestContactForm.class);

    private ContactForm contactForm = new ContactForm();

    public void fillUpForm(String forename, String surname, String telephone, String email, String message){
        contactForm.setForename(forename);
        contactForm.setSurname(surname);
        contactForm.setTelephone(telephone);
        contactForm.setEmail(email);
        contactForm.setMessage(message);

        browserActions.setText(By.xpath("//*[@id=\"forename\"]"), forename);
        browserActions.setText(By.xpath("//*[@id=\"surname\"]"), surname);
        browserActions.setText(By.xpath("//*[@id=\"email\"]"), telephone);
        browserActions.setText(By.xpath("//*[@id=\"telephone\"]"), email);
        browserActions.setText(By.xpath("//*[@id=\"message\"]"), message);
    }

    public void validate(String expectedResult) throws InterruptedException {
        String message = "";
        if (contactForm.getForename().isEmpty() || contactForm.getSurname().isEmpty() || contactForm.getEmail().isEmpty() ||
                contactForm.getTelephone().isEmpty()) {
            BrowserActions.waitUntilElementIsPresent(By.xpath("//*[@id=\"header-message\"]/div"));
            message = getText(By.xpath("//*[@id=\"header-message\"]/div"));
        } else {
            BrowserActions.waitUntilElementIsPresent(By.xpath("/html/body/div[2]/div/div[@class=\"alert alert-success\"]"));
            message = getText(By.xpath("/html/body/div[2]/div/div[@class=\"alert alert-success\"]"));
        }
        assertThat(message).isEqualTo(expectedResult);
    }



    public String getText(By locator) {
        logger.info("ACTION: GETTEXT, LOCATOR: " + locator.toString() + " ID: ");
        String text = findElement(locator).getText();
        logger.info("GETTEXT Value = " + text);
        return text;
    }
}
