package saucedemo.tests.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.hooks.Hooks;
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
}