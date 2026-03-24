package saucedemo.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import saucedemo.hooks.Hooks;


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
}
