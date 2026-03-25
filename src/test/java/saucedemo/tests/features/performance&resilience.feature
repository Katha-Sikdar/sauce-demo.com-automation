@Performance @Resilience @Regression
Feature: SauceDemo Performance and Resilience Testing

  @PerformanceGlitch
  Scenario: Login with performance glitch user handles delay
    Given I login using "performance_glitch_user" credentials from json
    Then I should see the product page successfully

  @ErrorUser @CheckoutBug
  Scenario: Verify error user cannot complete checkout due to field issues
    Given I login using "error_user" credentials from json
    And I add "Sauce Labs Backpack" to the cart
    And I navigate to the cart page
    And I click on the checkout button
    When I provide checkout details "Jannatul", "Katha", and "1200"
    Then I should see that the last name field is still empty or shows an error

  @ErrorUser @SortingBug
  Scenario: Verify error user sorting bug - Price Low to High
    Given I login using "error_user" credentials from json
    When I sort products by "Price (low to high)"
    Then the products should NOT be sorted correctly by price

  @ErrorUser @RemovalBug
  Scenario: Verify error user cart removal bug
    Given I login using "error_user" credentials from json
    And I add "Sauce Labs Backpack" to the cart
    And I navigate to the cart page
    When I click on the remove button for "Sauce Labs Backpack"
    Then the item "Sauce Labs Backpack" should still be in the cart