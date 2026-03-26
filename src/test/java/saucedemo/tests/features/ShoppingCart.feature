@Cart @Positive @Inventory @Regression
Feature: Cart and Inventory Management

  Background:
    Given I login using "valid_user" credentials from json

  @Smoke @Positive @Badge
  Scenario: Add a single item and verify cart badge updates
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should display "1"

  @Critical @MultiItem
  Scenario: Add multiple items and verify all appear in cart
    When I add the following items to the cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
    And I navigate to the cart page
    Then I should see all 3 items in the cart list

  @Sanity @Removal
  Scenario: Remove an item from the cart and verify cart state
    Given I have "Sauce Labs Backpack" in my cart
    When I remove "Sauce Labs Backpack" from the cart page
    Then the cart badge should disappear or decrease
    And "Sauce Labs Backpack" should not be in the cart list

  @Navigation @Persistence
  Scenario: Cart persists across page navigation
    When I add "Sauce Labs Backpack" to the cart
    And I navigate to the product details page for "Sauce Labs Backpack"
    And I go back to the inventory page
    Then the cart badge should still display "1"