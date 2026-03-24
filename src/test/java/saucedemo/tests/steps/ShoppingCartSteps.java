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

    @Given("I have {string} in my cart")
    public void ensureItemInCart(String item) {
        addItemToCart(item);
        verifyCartBadge("1");
    }

    @When("I remove {string} from the cart page")
    public void removeItemFromCart(String item) {
        navigateToCart();
        ShoppingCartPage.itemRemoveFromCart(item);

    }

    @And("{string} should not be in the cart list")
    public void shouldNotBeInTheCartList(String itemName) {
        boolean isPresent = ShoppingCartPage.isItemDisplayedInCart(itemName);

        Assert.assertFalse("Error: " + itemName + " is still visible in the cart!", isPresent);

        System.out.println("Success: " + itemName + " is no longer in the cart.");
    }

    @Then("the cart badge should disappear or decrease")
    public void verifyCartBadgeState() {
        List<WebElement> badge = driver.findElements(pageObjects.cartBadge);
        System.out.println("Cart Badge presence: " + !badge.isEmpty());
    }





    }
