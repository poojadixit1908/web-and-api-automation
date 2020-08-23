package com.wooliesx.framework.factory;

import com.wooliesx.framework.pageobjects.*;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    private static WebDriver driver = WebDriverProviderFactory.getBrowser();

    public static BasePage createPage(String pageName) {
        BasePage page = null;

        if (pageName.equals("homePage")) {
            page = new HomePage(driver);
        } else if (pageName.equals("productListingPage")) {
            page = new ProductListingPage(driver);
        } else if (pageName.equals("orderSummaryPage")) {
            page = new OrderSummaryPage(driver);
        } else if (pageName.equals("addressSummaryPage")) {
            page = new AddressSummaryPage(driver);
        } else if (pageName.equals("shippingPage")) {
            page = new ShippingPage(driver);
        } else if (pageName.equals("paymentsPage")) {
            page = new PaymentsPage(driver);
        }
        return page;
    }

}

