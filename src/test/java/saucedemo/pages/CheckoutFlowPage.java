package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutFlowPage {
    private static WebDriver driver;

    public CheckoutFlowPage(WebDriver driver) {
        this.driver = driver;
    }

    public static String getLastNameFieldValue() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            if (driver.findElements(pageObjects.lastNameField).size() > 0) {
                return driver.findElement(pageObjects.lastNameField).getAttribute("value");
            } else {
                return "FIELD_NOT_FOUND";
            }
        } catch (Exception e) {
            return "ERROR_IN_FINDING";
        }
    }


//    public void fillInformation(String fname, String lname, String zip) {
//        driver.findElement(pageObjects.firstNameField).sendKeys(fname);
//        driver.findElement(pageObjects.lastNameField).sendKeys(lname);
//        driver.findElement(pageObjects.postalCodeField).sendKeys(zip);
//    }

    public void fillInformation(String fname, String lname, String zip) {
        WebElement firstName = driver.findElement(pageObjects.firstNameField);
        firstName.clear();
        if (fname != null && !fname.isEmpty()) {
            firstName.sendKeys(fname);
        }

        WebElement lastName = driver.findElement(pageObjects.lastNameField);
        lastName.clear();
        if (lname != null && !lname.isEmpty()) {
            lastName.sendKeys(lname);
        }

        WebElement postalCode = driver.findElement(pageObjects.postalCodeField);
        postalCode.clear();
        if (zip != null && !zip.isEmpty()) {
            postalCode.sendKeys(zip);
        }
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