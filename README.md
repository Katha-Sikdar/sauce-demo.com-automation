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
   
 - Folder Structure:
 ```
 Sauce-Demo-Automation-Suites
├── .github/workflows
│   └── ci.yml                     # GitHub Actions (CI/CD) configuration
├── src
│   ├── test
│   │   ├── java
│   │   │   └── saucedemo
│   │   │       ├── base           # WebDriverFactory (Driver instantiation)
│   │   │       ├── hooks          # Setup, Teardown, and Screenshot logic
│   │   │       ├── pages          # PageObjects (Locators) & Business Logic
│   │   │       ├── utils          # ConfigReader and other helper utilities
│   │   │       └── tests
│   │   │           ├── runners    # Test execution entry points (JUnit)
│   │   │           └── steps      # Step Definitions (Gherkin-to-Java mapping)
│   │   └── resources
│   │       ├── config             # config.properties (Environment settings)
│   │       ├── features           # BDD Gherkin scenarios (.feature files)
│   │       └── fixtures           # testData.json (Externalized test data)
├── pom.xml                        # Maven dependencies and plugins
└── README.md                      # Project documentation

```


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
   
  - Tag Directory & Execution Guide

| Tag | Category | Description |
| :--- | :--- | :--- |
| @Smoke | **Priority** | Critical "Happy Path" tests (Login, Basic Checkout). |
| @Regression | **Full Suite** | Comprehensive tests covering all features and edge cases. |
| @Positive | **Functional** | Standard successful user journeys and flows. |
| @Negative | **Validation** | Error handling, missing fields, and incorrect inputs. |
| @Mathematical | **Logic** | Validates Price, Tax, and Order Total calculations. |
| @PerformanceGlitch | **Resilience** | Handles 5s delay using Explicit Waits for glitch users. |
| @ErrorUser | **Bug Detection** | Specifically targets sorting bugs and cart removal issues. |
| @VisualRegression | **UI/UX** | Detects broken images and UI mismatches (Problem User). |
| @Session | **Security** | Validates logout persistence and unauthorized URL access. |

---
  - Run All Tests:
    -      mvn clean test
 
- Run Specific Tags ( Smoke, Regression, Performance & Resilience etc.):
    -     mvn test -Dcucumber.filter.tags="@Performance or @Resilience"
    -     mvn test -Dcucumber.filter.tags="@Smoke"
    -     mvn test -Dcucumber.filter.tags="@Regression"
 
- Generate & View Report:
    -     mvn allure:serve
 

## 4. CI/CD Pipeline & Reporting
  This project is integrated with GitHub Actions to ensure continuous integration and automated testing on every code change.
  - How to View the Pipeline
      - Navigate to the "Actions" tab at the top of this GitHub repository.
      - You will see a list of recent workflow runs (e.g., CI/CD Test Suite).
      - Click on the latest run to see the execution logs and step-by-step progress of the tests.
        
  - How to View Reports
      - Allure Reports: After the pipeline execution, Allure results are processed. If configured with GitHub Pages, you can view the live report at: https://<your-username>.github.io/<repo-name>/
      - Artifacts: You can also download the test execution reports (HTML/XML) directly from the "Artifacts" section at the bottom of each GitHub Action run summary.
      - Failures: If a test fails in the pipeline, a screenshot of the failure is automatically captured and attached to the build artifacts for debugging.

  - Pipeline Trigger Policy
      - Push: Runs all tests on every push to the main branch.
      - Pull Request: Runs a regression suite before merging any PR to ensure no breaking changes.
   
        
## 5. Test Coverage Summary
This automation suite provides comprehensive coverage for all critical user journeys required in the assessment, including functional, resilience, and mathematical validation.
  - What is Covered?
      - Authentication: Valid/Invalid logins, locked-out users, and session security.
      - Product Catalog: Count validation, full sorting logic (A-Z, Z-A, Price), and problem_user image glitch detection (via src attribute check).
      - Shopping Cart: Multi-item management, badge updates, and state persistence across pages.
      - Checkout Flow: End-to-end purchase with mathematical validation of Tax ($8\%$) and Total Price.
      - Resilience: Explicit Wait strategies for performance_glitch_user and specific assertions for error_user failure states.

  - What is Intentionally Excluded & Why?
      - External Social Media Redirects:
          - Why: The footer contains links to Twitter, Facebook, and LinkedIn. I have verified their presence and clickable status but excluded validating the actual external page load. This avoids dependency on third-party site uptime and prevents the suite from being flagged by bot-detection on those platforms.
      - Pixel-to-Pixel Visual Comparison:
          - Why: For the problem_user scenario, I implemented Heuristic Image Validation (checking src attributes and image natural dimensions). Full pixel-perfect regression (like Applitools) was excluded to keep the framework lightweight and avoid "flaky" failures caused by minor browser-specific rendering differences.
      - Database-Level Assertions:
         - Why: SauceDemo is a mock frontend application without an accessible backend database. Therefore, all data persistence (like cart state) is validated at the UI-State level using Selenium, as there is no DB layer to query.
      - Stress and Volume Testing:
         - Why: While the suite handles performance delays using Smart Waits, it is not designed for load testing. Volume testing (adding 100+ items) was excluded as the site is limited to a fixed set of 6 products.
       
## 6. Identified Bugs (As Discovered by this Suite)
- While automating the **SauceDemo** platform, this suite successfully identified and asserted the following intentional system defects:

| User Context | Feature | Bug Description |
| :--- | :--- | :--- |
| **problem_user** | Inventory | Mismatched/Broken image links detected via `src` attribute & `naturalWidth` check. |
| **error_user** | Checkout | "Last Name" field fails to accept input or validate correctly during checkout. |
| **error_user** | Sorting | Sorting by "Price (Low to High)" fails to rearrange items in the correct numerical order. |
| **error_user** | Cart | Items fail to be removed from the shopping cart once added. |

---

## 7. Framework Technical Highlights
To ensure high reliability and senior-grade coding standards, the following were implemented:
-  **Explicit Wait Strategy:** Zero use of `Thread.sleep()`. All synchronizations are handled via `WebDriverWait` (Explicit/Fluent Waits) for optimal execution speed.
- **Thread-Safe Driver Management:** `WebDriverFactory` ensures clean browser instantiation and prevents memory leaks during teardown.
- **Data-Driven Architecture:** All test credentials and UI messages are externalized into `testData.json` and `config.properties`.
- **Automated Screenshots:** Screenshots are automatically captured and attached to the report for every failed step to facilitate rapid debugging.
- **Modular Page Objects:** Page classes are strictly separated into `Locators` and `Action Logic` to maximize code reusability.

---

## 8. Test Execution Report
The framework is configured to generate detailed graphical reports using **Allure** and **Cucumber HTML Reports** after every execution.

### **Current Execution Summary**

| Metric | Details  |
| :--- |:---------|
| **Total Test Scenarios** | 17       |
| **Passed** | 17       |
| **Failed** | 0        |
| **Success Rate** |  **100%**|
| **Average Execution Time** | ~73 seconds |

### **How to View Detailed Reports Locally**

  - Allure Report (Advanced Dashboard):**

    To see a rich graphical dashboard with trend analysis and failure screenshots:

     -      mvn allure:serve

  - Cucumber HTML Report (Lightweight):

    - A standard HTML report is automatically generated after running ```mvn clean test```. You can find it at: ```target/cucumber-reports/index.html```

  - Here is a summary dashboard from the latest test execution showing 100% success rate across all critical features.
      
![Cucumber Test Report Summary](<img width="1470" height="552" alt="Screenshot 2026-03-26 at 16 18 21" src="https://github.com/user-attachments/assets/0b5e5586-a818-44b9-addf-0cb84e588abd" />)


      
 
