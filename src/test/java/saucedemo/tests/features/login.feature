
@Login @Auth @Regression
Feature: User Authentication Tests

  Background:
    Given I am on the Sauce Demo login page

  @Positive @Smoke @HappyPath
  Scenario: Successful login with valid credentials
    When I login using "valid_user" credentials from json
    Then I should see the products inventory page

  @Negative @DataDriven @ErrorHandling
  Scenario Outline: Login failure with various invalid inputs
    When I login using "<user_key>" credentials from json
    Then I should see an error message containing "<error_text>"

    Examples:
      | user_key              | error_text                                          |
      | invalid_password_user | Username and password do not match                  |
      | empty_username_user   | Username is required                                |
      | empty_password_user   | Password is required                                |
      | sql_injection_user    | Username and password do not match                  |

  @Negative @AccountSecurity @LockedOut
  Scenario: Validation for locked-out user
    When I login using "locked_out_user" credentials from json
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."

  @Session @Security @Logout
  Scenario: Session logout and persistence check
    When I login using "valid_user" credentials from json
    And I logout from the application
    Then I should be redirected back to the login page
    And I should not be able to access the inventory page directly