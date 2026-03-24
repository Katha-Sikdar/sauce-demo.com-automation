@Checkout @Regression
Feature: Checkout Flow (End-to-End)

  Background:
    Given I login using "valid_user" credentials from json
    And I add "Sauce Labs Backpack" to the cart
    And I navigate to the cart page
    And I click on the checkout button

  @Positive @Success
  Scenario: Complete a full purchase with valid details
    When I provide checkout details "Jannatul", "Katha", and "1200"
    Then the summary totals should be mathematically correct
    And I finish the order
    Then I should see the order success message "Thank you for your order!"

  @Negative @Validation
  Scenario Outline: Checkout blocked when required fields are missing
    When I provide checkout details "<firstName>", "<lastName>", and "<postalCode>"
    Then I should see an error message containing "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage             |
      |           | Katha    | 1200       | First Name is required   |
      | Jannatul  |          | 1200       | Last Name is required    |
      | Jannatul  | Katha    |            | Postal Code is required  |

  @Mathematical @Summary
  Scenario: Verify order summary is mathematically correct
    When I add "Sauce Labs Bike Light" to the cart
    And I navigate to the cart page
    And I click on the checkout button
    And I provide checkout details "Jannatul", "Katha", and "1200"
    Then the summary totals should be mathematically correct