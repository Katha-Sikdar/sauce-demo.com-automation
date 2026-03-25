Feature: SauceDemo Performance and Resilience Testing

  @Performance @Resilience
Scenario: Login with performance glitch user handles delay
    Given I login using "performance_glitch_user" credentials from json
    Then I should see the product page successfully