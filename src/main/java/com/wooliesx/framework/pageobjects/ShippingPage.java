package com.wooliesx.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShippingPage extends BasePage {

    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnTermsOfServiceCheckbox() {
        WebElement termsCheckboxElement = driver.findElement(By.id("uniform-cgv"));
        termsCheckboxElement.click();
    }

    public void clickCheckout() {
        WebElement checkoutButton = driver.findElement(By.name("processCarrier"));
        waitForVisibilityAndClick(checkoutButton);
    }

    public void clickOnPaymentType(String paymentType) {
        WebElement paymentTypeElement = driver.findElement(By.className(paymentType));
        paymentTypeElement.click();
    }
}
