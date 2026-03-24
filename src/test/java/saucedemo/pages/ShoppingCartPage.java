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

    public static void itemRemoveFromCart(String item) {
       By removeCartBtn= pageObjects.getRemoveBtnByItemName(item);
        driver.findElement(removeCartBtn).click();
        System.out.println("Removed item from cart: " + item);
     //String xpath = "//div[text()='" + item + "']/ancestor::div[@class='cart_item']//button";
//        driver.findElement(By.xpath(xpath)).click();
    }

    public static boolean isItemDisplayedInCart(String itemName) {
        // কার্ট পেজের আইটেম নেম গুলোর XPath
        String xpath = "//div[@class='cart_item']//div[text()='" + itemName + "']";
        List<WebElement> items = driver.findElements(By.xpath(xpath));

        // যদি লিস্ট খালি হয় (size == 0), তার মানে আইটেমটি নেই
        return !items.isEmpty();
    }
}
