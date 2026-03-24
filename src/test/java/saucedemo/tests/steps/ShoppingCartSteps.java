package saucedemo.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.hooks.Hooks;
import saucedemo.pages.ShoppingCartPage;
import saucedemo.pages.pageObjects;

import java.util.List;

public class ShoppingCartSteps {
    private WebDriver driver = Hooks.getDriver();

    @When("I add {string} to the cart")
    public static void addItemToCart(String itemName) {
        ShoppingCartPage.productAddCartButton(itemName);

    }

    @Then("the cart badge should display {string}")
    public void verifyCartBadge(String count) {
        ShoppingCartPage.cartBadgeVerification(count);

    }

    @When("I add the following items to the cart:")
    public void addMultipleItems(DataTable dataTable) {
        ShoppingCartPage.MultipleItemsAddCartButton(dataTable);
    }

    @And("I navigate to the cart page")
    public void navigateToCart() {
        driver.findElement(pageObjects.shoppingCartLink).click();
    }

    @Then("I should see all {int} items in the cart list")
    public void verifyItemsInCart(int expectedCount) {
        ShoppingCartPage.cartVerificationInItem(expectedCount);


    }


}