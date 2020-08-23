package com.wooliesx.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressSummaryPage extends BasePage {

    public AddressSummaryPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutOnAddressPage() {
        WebElement checkoutButton = driver.findElement(By.name("processAddress"));
        waitForVisibilityAndClick(checkoutButton);
    }
}
