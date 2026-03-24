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

//    private By cartBadge = By.className("shopping_cart_badge");
//    private By cartLink = By.className("shopping_cart_link");
//    private By cartItemName = By.className("inventory_item_name");

    @When("I add {string} to the cart")
    public void addItemToCart(String itemName) {
        ShoppingCartPage.productAddCartButton(itemName);

    }

    @Then("the cart badge should display {string}")
    public void verifyCartBadge(String count) {
        ShoppingCartPage.cartBadgeVerification(count);

    }


}