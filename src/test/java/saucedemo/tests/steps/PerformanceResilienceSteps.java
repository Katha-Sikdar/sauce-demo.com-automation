package saucedemo.tests.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.hooks.Hooks;
import saucedemo.pages.CheckoutFlowPage;
import saucedemo.pages.pageObjects;
import java.time.Duration;

public class PerformanceResilienceSteps {
    private WebDriver driver = Hooks.getDriver();

    @Then("I should see the product page successfully")
    public void verifyProductPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.productTitle));

            boolean isDisplayed = driver.findElement(pageObjects.productTitle).isDisplayed();
            Assert.assertTrue("Error: Product page did not load after login!", isDisplayed);

            System.out.println("LOG: Login successful and Product page is visible.");
        } catch (Exception e) {
            Assert.fail("Login took too long or Product page was not found: " + e.getMessage());
        }
    }

    @And("I navigate to the checkout page")
    public void navigateToCheckout() {
        driver.findElement(pageObjects.shoppingCartLink).click();
        driver.findElement(pageObjects.checkoutBtn).click();
    }

    @Then("I should see that the last name field is still empty or shows an error")
    public void verifyLastNameError() {
        String lastNameValue = CheckoutFlowPage.getLastNameFieldValue();

        if (lastNameValue.equals("FIELD_NOT_FOUND")) {
            System.out.println("LOG: Identifying error_user bug - System redirected without valid Last Name.");
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue("Error: Last name field should be empty for error_user!", lastNameValue.isEmpty());
        }
    }
}