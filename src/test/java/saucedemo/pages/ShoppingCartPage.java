package saucedemo.pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import saucedemo.hooks.Hooks;
import saucedemo.tests.steps.ShoppingCartSteps;

import java.util.List;


public class ShoppingCartPage {

    private static WebDriver driver = Hooks.getDriver();

    public static void productAddCartButton(String itemName) {
        By addToCartBtn = pageObjects.getAddToCartBtnByItemName(itemName);

        driver.findElement(addToCartBtn).click();
        System.out.println("Clicked Add to Cart for: " + itemName);
    }

    public static void cartBadgeVerification(String count) {
        String actualCount = driver.findElement(pageObjects.cartBadge).getText();
        Assert.assertEquals("Badge count mismatch!", count, actualCount);

    }

    public static void MultipleItemsAddCartButton(DataTable dataTable) {
        List<String> items = dataTable.asList();
        for (String item : items) {
            ShoppingCartSteps.addItemToCart(item);
        }
    }

    public static void cartVerificationInItem(int expectedCount) {
        List<WebElement> items = driver.findElements(pageObjects.cartItemName);
        Assert.assertEquals("Cart item count mismatch!", expectedCount, items.size());
    }
}
