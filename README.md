# SauceDemo Automation Suite

This is a robust, behavior-driven development (BDD) automation framework designed for the SauceDemo e-commerce platform. It focuses on end-to-end checkout flows, mathematical accuracy of order totals, and resilience against UI glitches and intentional bugs (Error User).

## 1. Framework Choice & Rationale
  
   ### The Stack:
    - Language: Java 24 (Latest JDK)
    - Automation Tool: Selenium WebDriver (4.18.1)
    - Test Runner: JUnit 4
    - BDD Framework: Cucumber 7
    - Reporting: Allure Reports / Cucumber HTML Reports
    - Build Tool: Maven

  ### Why this stack?
   - Selenium & Java: Chosen for its maturity, vast community support, and strong type-safety. Selenium 4 provides better support for relative locators and Chrome DevTools (CDP).
   - Cucumber (BDD): Essential for making test cases readable for non-technical stakeholders (Product Owners/Manual QAs).
   - Maven: Simplifies dependency management and integrates seamlessly with CI/CD pipelines.

 ### Alternatives Considered:
  - Playwright (Node.js/Java): Considered for its speed and auto-waiting features, but Selenium was chosen due to its widespread industry adoption and specific requirements for handling legacy browser behaviors (like the performance_glitch_user delay).
  - TestNG: While powerful, JUnit was selected for its simplicity and direct integration with Cucumber’s native runner.

## 2. Architecture Overview
 - The framework follows the Page Object Model (POM) combined with Cucumber Hooks to ensure clean separation between test logic and UI locators.
   
 ###Folder Structure:


 ## 3. Setup & Run Instructions
  - Prerequisites:
  -     Java JDK 24 or higher
  -     Maven 3.8+
  -     Google Chrome (Latest Version)

 - Steps to Run Locally:
   - Clone the Repository:
     -     git clone https://github.com/your-username/Sauce-Demo-Automation-Suites.git

  - Install Dependencies:
     -     mvn clean install -DskipTests
   
  - Run All Tests:
    -      mvn test
   
  - Run Specific Tags (e.g., Performance & Resilience):
    -     mvn test -Dcucumber.filter.tags="@Performance or @Resilience"
