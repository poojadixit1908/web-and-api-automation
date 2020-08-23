package com.wooliesx.framework.steps;

import com.wooliesx.framework.factory.PageFactory;
import com.wooliesx.framework.factory.WebDriverProviderFactory;
import com.wooliesx.framework.pageobjects.*;
import com.wooliesx.framework.util.ConfigurationLoader;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.Properties;

public class CheckoutFeatureStepDef {

    private static Properties properties;

    @Before
    public void setUp() {
        properties = ConfigurationLoader.loadProperties();
    }

    @After
    public void after() {
        WebDriverProviderFactory.quitDriver();
    }


    @Given("^User is on the homepage$")
    public void userIsOnTheHomepage() {
        String homePageUrl = properties.getProperty("automation.website.home.page.url");
        BasePage homePage = PageFactory.createPage("homePage");
        homePage.goToUrl(homePageUrl);
    }


    @And("^User sign with valid credentials$")
    public void userSignWithValidCredentials() {
        HomePage homePage = (HomePage) PageFactory.createPage("homePage");
        homePage.signInWithValidCredentials();
        String currentPageUrl = homePage.getCurrentPageUrl();
        Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", currentPageUrl);


    }

    @And("^User add two items in the cart$")
    public void userAddTwoItemsInTheCart() {
        ProductListingPage productListingPage = (ProductListingPage) PageFactory.createPage("productListingPage");
        productListingPage.clickOnWomenCategory();
        productListingPage.addItemInTheCart(0);
        productListingPage.clickContinueShopping();
        //have to do this workaround to add second product in the cart
        userIsOnTheHomepage();
        productListingPage.addItemInTheCart(1);
    }

    @And("^User click on the checkout button and proceed to checkout$")
    public void userClickOnTheCheckoutButtonAndProceedToCheckout() {
        ProductListingPage productListingPage = (ProductListingPage) PageFactory.createPage("productListingPage");
        productListingPage.clickCheckout();
    }

    @Then("^There should be two items in the cart$")
    public void thereShouldBeTwoItemsInTheCart() {
        ProductListingPage productListingPage = (ProductListingPage) PageFactory.createPage("productListingPage");
        int numberOfCartItems = productListingPage.getNumberOfCartItems();
        Assert.assertEquals("There should be 2 items in the cart", 2, numberOfCartItems);
    }

    @Then("^User should be taken to address details page$")
    public void userShouldBeTakenToAddressDetailsPage() {
        ProductListingPage productListingPage = (ProductListingPage) PageFactory.createPage("productListingPage");
        String currentPageUrl = productListingPage.getCurrentPageUrl();
        Assert.assertEquals("http://automationpractice.com/index.php?controller=order", currentPageUrl);
    }

    @And("^User proceeds to checkout$")
    public void userProceedsToCheckout() {
        OrderSummaryPage orderSummaryPage = (OrderSummaryPage) PageFactory.createPage("orderSummaryPage");
        orderSummaryPage.clickOnCheckoutButton();
        AddressSummaryPage addressSummaryPage = (AddressSummaryPage) PageFactory.createPage("addressSummaryPage");
        addressSummaryPage.clickCheckoutOnAddressPage();
    }

    @And("^User agrees to terms of service and clicks on proceed to checkout page button$")
    public void userAgreesToTermsOfServiceAndClicksOnProceedToCheckoutPageButton() {
        ShippingPage shippingPage = (ShippingPage) PageFactory.createPage("shippingPage");
        shippingPage.clickOnTermsOfServiceCheckbox();
        shippingPage.clickCheckout();
    }

    @And("^User choose to pay by \"([^\"]*)\" option$")
    public void userChooseToPayByOption(String paymentOption) {
        ShippingPage shippingPage = (ShippingPage) PageFactory.createPage("shippingPage");
        shippingPage.clickOnPaymentType("bankwire");
    }

    @And("^User clicks on the confirm my order button$")
    public void userClicksOnTheConfirmMyOrderButton() {
        PaymentsPage paymentsPage = (PaymentsPage) PageFactory.createPage("paymentsPage");
        paymentsPage.clickOnOrderConfirmationButton();
    }

    @Then("^Order confirmation page should be shown with the order details with message \"([^\"]*)\"$")
    public void orderConfirmationPageShouldBeShownWithTheOrderDetailsWithMessage(String message) {
        PaymentsPage paymentsPage = (PaymentsPage) PageFactory.createPage("paymentsPage");
        String pageHeadingText = paymentsPage.getPageHeadingText();
        Assert.assertEquals("Heading should be Order confirmation", "ORDER CONFIRMATION", pageHeadingText);
        String orderConfirmationMsg = paymentsPage.getOrderConfirmationMsg();
        Assert.assertEquals("Should have correct order confirmation message", message, orderConfirmationMsg);
    }
}
