@Products @Catalog @Regression
Feature: Product Catalog and Visual Validation

  Background:
    Given I am on the Sauce Demo login page

  @Positive @Smoke @InventoryCount
  Scenario: Verify product listing loads correctly
    When I login using "valid_user" credentials from json
    Then I verify product count is 6

  @Sorting @Functional @DataDriven
  Scenario Outline: Verify product sorting functionality
    When I login using "valid_user" credentials from json
    And I select sort option "<sort_option>"
    Then I verify the products are sorted by "<sort_type>"

    Examples:
      | sort_option         | sort_type         |
      | Name (A to Z)       | Name A to Z       |
      | Name (Z to A)       | Name Z to A       |
      | Price (low to high) | Price Low to High |
      | Price (high to low) | Price High to Low |

  @VisualRegression @ProblemUser @UIBug
  Scenario: Detect broken or mismatched images for problem user
    When I login using "problem_user" credentials from json
    Then I verify that all product images are unique and not broken