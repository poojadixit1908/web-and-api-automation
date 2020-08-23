package com.wooliesx.framework.pageobjects;

import com.wooliesx.framework.util.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void signInWithValidCredentials() {
        //get username and password from properties file
        Properties properties = ConfigurationLoader.loadProperties();
        String username = properties.getProperty("automation.website.username");
        String password = properties.getProperty("automation.website.password");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement signIn = driver.findElement(By.className("login"));
        signIn.click();
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("passwd")).sendKeys(password);
        WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
        submitLogin.click();

    }


}

