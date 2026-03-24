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



}

