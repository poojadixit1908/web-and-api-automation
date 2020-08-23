package com.wooliesx.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentsPage extends BasePage {

    public PaymentsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnOrderConfirmationButton() {
        WebElement orderConfirmationButton = driver.findElement(By.cssSelector("button.btn.btn-default.button-medium"));
        orderConfirmationButton.click();
    }

    public String getPageHeadingText() {
        WebElement pageHeadingElement = driver.findElement(By.className("page-heading"));
        return pageHeadingElement.getText();
    }

    public String getOrderConfirmationMsg() {
        WebElement pElement = driver.findElement(By.className("cheque-indent"));
        return pElement.findElement(By.tagName("strong")).getText();
    }

}
