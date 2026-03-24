package saucedemo.tests.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.hooks.Hooks;
import saucedemo.pages.CheckoutFlowPage;
import saucedemo.pages.pageObjects;


public class CheckoutFlowSteps {
    private WebDriver driver;
    private CheckoutFlowPage checkoutPage = new CheckoutFlowPage(Hooks.getDriver());

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

    @Then("I should see the order success message {string}")
    public void verifySuccess(String expectedMsg) {
        Hooks.getDriver().findElement(pageObjects.finishBtn).click();
        String actualMsg = Hooks.getDriver().findElement(pageObjects.completeHeader).getText();
        Assert.assertEquals("Order not completed!", expectedMsg, actualMsg);
    }

//    @And("I finish the order")
//    public void i_finish_the_order() {
//        checkoutPage.clickFinish();
//
//        System.out.println("LOG: Order finished successfully.");
//    }

    @And("I finish the order")
    public void clickFinish() {
    WebElement finishButton = driver.findElement(pageObjects.finishBtn);

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButton);

    try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

    finishButton.click();
}
}