package saucedemo.pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private static WebDriver driver;
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }


    public void productAddCartButton(String itemName) {
        By addToCartBtn = pageObjects.getAddToCartBtnByItemName(itemName);
        driver.findElement(addToCartBtn).click();
        System.out.println("LOG: Clicked Add to Cart for: " + itemName);
    }

    public void cartBadgeVerification(String count) {
        String actualCount = driver.findElement(pageObjects.cartBadge).getText();
        Assert.assertEquals("Badge count mismatch!", count, actualCount);
    }

    public void MultipleItemsAddCartButton(DataTable dataTable) {
        List<String> items = dataTable.asList();
        for (String item : items) {
            productAddCartButton(item);
        }
    }

    public void cartVerificationInItem(int expectedCount) {
        List<WebElement> items = driver.findElements(pageObjects.cartItemName);
        Assert.assertEquals("Cart item count mismatch!", expectedCount, items.size());
    }

    public static void itemRemoveFromCart(String item) {
        By removeCartBtn = pageObjects.getRemoveBtnByItemName(item);
        driver.findElement(removeCartBtn).click();
        System.out.println("LOG: Removed item from cart: " + item);
    }

    public static boolean isItemDisplayedInCart(String itemName) {
        pageObjects.getCartItemByName(itemName);
        List<WebElement> items = driver.findElements(pageObjects.getCartItemByName(itemName));
        return !items.isEmpty();
    }

    public String getCartBadgeCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.cartBadge)).getText();
        } catch (Exception e) {
            return "0";
        }
    }
}