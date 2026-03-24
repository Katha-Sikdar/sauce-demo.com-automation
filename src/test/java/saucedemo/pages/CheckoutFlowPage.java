package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutFlowPage {
    private WebDriver driver;

    public CheckoutFlowPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInformation(String fname, String lname, String zip) {
        driver.findElement(pageObjects.firstNameField).sendKeys(fname);
        driver.findElement(pageObjects.lastNameField).sendKeys(lname);
        driver.findElement(pageObjects.postalCodeField).sendKeys(zip);
    }

    public void clickContinue() {
        driver.findElement(pageObjects.continueBtn).click();
    }

    public double getPriceFromElement(org.openqa.selenium.By locator) {
        String rawText = driver.findElement(locator).getText();
        return Double.parseDouble(rawText.replaceAll("[^0-9.]", ""));
    }

    public void clickFinish() {
        driver.findElement(pageObjects.finishBtn).click();
    }
}