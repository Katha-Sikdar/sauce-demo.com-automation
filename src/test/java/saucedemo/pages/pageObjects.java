package saucedemo.pages;

import org.openqa.selenium.By;

public class pageObjects {

    //Login Page
    public static final By usernameFld = By.xpath("//input[@data-test='username']");
    public static final By passwordFld = By.xpath("//input[@data-test='password']");
    public static final By loginBtn    = By.xpath("//input[@data-test='login-button']");
    public static final By errorMsg    = By.xpath("//h3[@data-test='error']");

    //Log Out
    public static final By menuBtn     = By.xpath("//button[@id='react-burger-menu-btn']");
    public static final By logoutLink  = By.xpath("//a[@id='logout_sidebar_link']");


    //Product Inventory Page
    public static final By inventoryList = By.xpath("//div[@data-test='inventory-list']");
    public static final By inventoryItemName = By.xpath("//div[@data-test='inventory-item-name']");
    public static final By inventoryItemPrice = By.xpath("//div[@data-test='inventory-item-price']");
    public static final By inventoryItemImg = By.xpath("//img[contains(@class, 'inventory_item_img')]");
    public static final By sortDropdown = By.xpath("//select[@data-test='product-sort-container']");

    //Shopping Cart Page
    public static final By shoppingCartLink = By.xpath("//a[@data-test='shopping-cart-link']");
    public static final By cartBadge = By.xpath("//span[@data-test='shopping-cart-badge']");
    public static final By cartItemName = By.xpath("//div[@data-test='inventory-item-name']");

    //Product Add to CART

    public static By getAddToCartBtnByItemName(String itemName) {
        return By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button");
    }

    //Product Remove from CART
    public static By getRemoveBtnByItemName(String itemName) {
        String formattedName = itemName.toLowerCase().replace(" ", "-");
        String xpath = "//button[@data-test='remove-" + formattedName + "']";
        return By.xpath(xpath);
    }

    //Product Details Page
    public static By getProductByName(String itemName) {
        String xpath = "//div[text()='" + itemName + "']";
        return By.xpath(xpath);

    }

    // PageObjects.java
    public static final By cartBadgeVerify = By.xpath("//span[@data-test='shopping-cart-badge']");

    public static By getCartItemByName(String itemName) {
        String xpath = "//div[@class='cart_item']//div[text()='" + itemName + "']";
        return By.xpath(xpath);
    }

    // Checkout Buttons & Fields
    public static final By checkoutBtn = By.xpath("//button[@id='checkout' or @data-test='checkout']");
    public static final By firstNameField = By.xpath("//input[@id='first-name']");
    public static final By lastNameField = By.xpath("//input[@id='last-name']");
    public static final By postalCodeField = By.xpath("//input[@id='postal-code']");
    public static final By continueBtn = By.xpath("//input[@id='continue']");
    public static final By finishBtn = By.xpath("//button[@id='finish']");
    public static final By errorMessage = By.xpath("//h3[@data-test='error']");

    // Summary Labels
    public static final By itemTotalLabel = By.xpath("//div[@class='summary_subtotal_label']");
    public static final By taxLabel = By.xpath("//div[@class='summary_tax_label']");
    public static final By totalLabel = By.xpath("//div[@class='summary_total_label' and contains(@class, 'total_label')]");
    public static final By completeHeader = By.xpath("//h2[@class='complete-header']");
    public static final By completeOrderBtn = By.xpath("//button[@id='finish' or @name='finish']");

    // Dynamic Locator for Cart/Inventory Items
   // public static By getProductByName(String itemName) {
       // return By.xpath("//div[text()='" + itemName + "']");

}

