package com.wooliesx.framework.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductListingPage extends BasePage {

    public ProductListingPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnWomenCategory() {
        WebElement topBlockMenuDiv = driver.findElement(By.id("block_top_menu"));
        WebElement ulElement = topBlockMenuDiv.findElement(By.tagName("ul"));
        WebElement firstLiElement = ulElement.findElement(By.tagName("li"));
        firstLiElement.findElement(By.tagName("a")).click();
    }

    public void addItemInTheCart(int item) {
        WebElement productListGridRow = driver.findElement(By.cssSelector(".product_list.grid.row"));
        List<WebElement> liElements = productListGridRow.findElements(By.tagName("li"));
        WebElement productLiElement = liElements.get(item);
        addProduct(productLiElement);
    }

    public void clickContinueShopping() {
        //click on continue to shopping
        waitForSeconds(2);
        WebElement continueShoppingButton = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/span/span"));
        waitForVisibilityAndClick(continueShoppingButton);
    }

    public void clickCheckout() {
        //click on continue to shopping
        waitForSeconds(2);
        WebElement continueShoppingButton = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a/span"));
        waitForVisibilityAndClick(continueShoppingButton);
    }

    private void addProduct(WebElement productLiElement) {
        WebElement productContainerDiv = productLiElement.findElement(By.className("product-container"));
        WebElement rightBlockDiv = productContainerDiv.findElement(By.className("right-block"));
        WebElement buttonContainerDiv = rightBlockDiv.findElement(By.className("button-container"));
        WebElement addToCartButton = buttonContainerDiv.findElement(By.tagName("a"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addToCartButton);
    }

    public int getNumberOfCartItems() {
        WebElement cartQuantityElement = driver.findElement(By.className("ajax_cart_quantity"));
        String text = cartQuantityElement.getText();
        return Integer.parseInt(text);
    }

}
