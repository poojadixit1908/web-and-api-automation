# new feature
# Tags: optional
@websmoke
Feature: Test checkout flow via  adding 2 items to the cart and place an order

  Scenario: I want to add 2 items to the cart and place an order
    Given User is on the homepage
    And User sign with valid credentials
    And User add two items in the cart
    And User click on the checkout button and proceed to checkout
    Then There should be two items in the cart
    Then User should be taken to address details page
    And User proceeds to checkout
    And User agrees to terms of service and clicks on proceed to checkout page button
    And User choose to pay by "bankwire" option
    And User clicks on the confirm my order button
    Then Order confirmation page should be shown with the order details with message "Your order on My Store is complete."


