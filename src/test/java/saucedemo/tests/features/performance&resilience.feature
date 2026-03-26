@Performance @Resilience @Regression @FaultTolerance
Feature: SauceDemo Performance and Resilience Testing

  @PerformanceGlitch @WaitStrategy @Smoke
  Scenario: Login with performance glitch user handles delay
    Given I login using "performance_glitch_user" credentials from json
    Then I should see the product page successfully

  @ErrorUser @CheckoutBug @Negative
  Scenario: Verify error user cannot complete checkout due to field issues
    Given I login using "error_user" credentials from json
    And I add "Sauce Labs Backpack" to the cart
    And I navigate to the cart page
    And I click on the checkout button
    When I provide checkout details "Jannatul", "Katha", and "1200"
    Then I should see that the last name field is still empty or shows an error

  @ErrorUser @SortingBug @UIBug @DataDriven
  Scenario Outline: Verify error user sorting bug - Price Low to High
    Given I login using "error_user" credentials from json
    And I select sort option "<sort_option>"
    Then I verify the products are sorted by "<sort_type>"
    Then the products should NOT be sorted correctly by price

    Examples:
      | sort_option         | sort_type         |
      | Price (low to high) | Price Low to High |
      #| Price (high to low) | Price High to Low |


  @ErrorUser @RemovalBug @StatePersistence
  Scenario: Verify error user cart removal bug
    Given I login using "error_user" credentials from json
    And I add "Sauce Labs Onesie" to the cart
    And I navigate to the cart page
    When I click on the remove button for "Sauce Labs Onesie"
    Then the item "Sauce Labs Onesie" should still be in the cart