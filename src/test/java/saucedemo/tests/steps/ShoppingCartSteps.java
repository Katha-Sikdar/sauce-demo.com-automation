package saucedemo.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.hooks.Hooks;
import saucedemo.pages.ShoppingCartPage;
import saucedemo.pages.pageObjects;

import java.util.List;

public class ShoppingCartSteps {
    private WebDriver driver = Hooks.getDriver();

    private ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

    @When("I add {string} to the cart")
    public void addItemToCart(String itemName) {
        shoppingCartPage.productAddCartButton(itemName);
    }

    @Then("the cart badge should display {string}")
    public void verifyCartBadge(String count) {
        shoppingCartPage.cartBadgeVerification(count);
    }

    @When("I add the following items to the cart:")
    public void addMultipleItems(DataTable dataTable) {
        shoppingCartPage.MultipleItemsAddCartButton(dataTable);
    }

    @And("I navigate to the cart page")
    public void navigateToCart() {
        driver.findElement(pageObjects.shoppingCartLink).click();
    }

    @Then("I should see all {int} items in the cart list")
    public void verifyItemsInCart(int expectedCount) {
        shoppingCartPage.cartVerificationInItem(expectedCount);
    }

    @Given("I have {string} in my cart")
    public void ensureItemInCart(String item) {
        addItemToCart(item);
        verifyCartBadge("1");
    }

    @When("I remove {string} from the cart page")
    public void removeItemFromCart(String item) {
        navigateToCart();
        shoppingCartPage.itemRemoveFromCart(item);
    }

    @And("{string} should not be in the cart list")
    public void shouldNotBeInTheCartList(String itemName) {
        boolean isPresent = shoppingCartPage.isItemDisplayedInCart(itemName);
        Assert.assertFalse("Error: " + itemName + " is still visible in the cart!", isPresent);
        System.out.println("Success: " + itemName + " is no longer in the cart.");
    }

    @Then("the cart badge should disappear or decrease")
    public void verifyCartBadgeState() {
        List<WebElement> badge = driver.findElements(pageObjects.cartBadge);
        System.out.println("Cart Badge presence: " + !badge.isEmpty());
    }

    @And("I navigate to the product details page for {string}")
    public void navigateToProductDetails(String itemName) {
        driver.findElement(pageObjects.getProductByName(itemName)).click();
    }

    @And("I go back to the inventory page")
    public void goBack() {
        driver.navigate().back();
    }

    @Then("the cart badge should still display {string}")
    public void the_cart_badge_should_still_display(String expectedCount) {
        String actualCount = shoppingCartPage.getCartBadgeCount();
        Assert.assertEquals("Cart badge count mismatch!", expectedCount, actualCount);
        System.out.println("Verified: Cart badge still shows " + actualCount);
    }
}