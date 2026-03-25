package saucedemo.tests.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import saucedemo.hooks.Hooks;
import saucedemo.pages.CheckoutFlowPage;
import saucedemo.pages.pageObjects;

import java.time.Duration;


public class CheckoutFlowSteps {
    //private WebDriver driver;
    private WebDriver driver;
    private CheckoutFlowPage checkoutPage;

    public CheckoutFlowSteps() {
        this.driver = Hooks.getDriver();
        this.checkoutPage = new CheckoutFlowPage(this.driver);
    }
    @And("I click on the checkout button")
    public void goToCheckout() {
        Hooks.getDriver().findElement(pageObjects.checkoutBtn).click();
    }

    @When("I provide checkout details {string}, {string}, and {string}")
    public void provideDetails(String f, String l, String z) {
        checkoutPage.fillInformation(f, l, z);
        checkoutPage.clickContinue();
    }

    @Then("the summary totals should be mathematically correct")
    public void verifyTotals() {
        double subTotal = checkoutPage.getPriceFromElement(pageObjects.itemTotalLabel);
        double tax = checkoutPage.getPriceFromElement(pageObjects.taxLabel);
        double actualTotal = checkoutPage.getPriceFromElement(pageObjects.totalLabel);
        Assert.assertEquals("Summary Total Mismatch!", (subTotal + tax), actualTotal, 0.01);
    }

    @And("I finish the order")
    public void clickFinish() {

        if (driver == null) driver = Hooks.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishButton = driver.findElement(pageObjects.finishBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButton);
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
        System.out.println("LOG: Scrolled and clicked Finish button successfully.");
    }

    @Then("I should see the order success message {string}")
    public void verifySuccess(String expectedMsg) {

        if (driver == null) driver = Hooks.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.completeHeader));

        String actualMsg = header.getText();
        Assert.assertEquals("Order message mismatch!", expectedMsg, actualMsg);
        System.out.println("Success! Order message verified: " + actualMsg);
    }

}