package SauceDemo.pages;

import org.openqa.selenium.By;

public class pageObjects {

    public static final By usernameFld = By.xpath("//input[@data-test='username']");
    public static final By passwordFld = By.xpath("//input[@data-test='password']");
    public static final By loginBtn    = By.xpath("//input[@data-test='login-button']");
    public static final By errorMsg    = By.xpath("//h3[@data-test='error']");

    public static final By menuBtn     = By.xpath("//button[@id='react-burger-menu-btn']");
    public static final By logoutLink  = By.xpath("//a[@id='logout_sidebar_link']");
    public static final By inventoryList = By.xpath("//div[@class='inventory_list']");
}

