package com.planitestexam.bdd.pages;

import com.planitestexam.bdd.implementation.ContactForm;
import com.planitestexam.bdd.uitest.BrowserActions;
import org.openqa.selenium.By;

public class planittestContactForm {
    private static BrowserActions browserActions = BrowserActions.getInstance();
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

}
