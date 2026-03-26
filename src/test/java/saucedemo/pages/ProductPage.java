package saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static saucedemo.pages.pageObjects.*;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryList));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItemName));
        return driver.findElements(inventoryItemName).size();
    }

    public void selectSortOption(String optionText) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(dropdown);
        select.selectByVisibleText(optionText);
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItemName));
        List<WebElement> elements = driver.findElements(inventoryItemName);
        List<String> names = new ArrayList<>();
        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

//    public List<Double> getProductPrices() {
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItemPrice));
//        List<WebElement> elements = driver.findElements(inventoryItemPrice);
//        List<Double> prices = new ArrayList<>();
//        for (WebElement el : elements) {
//
//            String priceText = el.getText().replace("$", "");
//            prices.add(Double.parseDouble(priceText));
//        }
//        return prices;
//    }

    public List<Double> getProductPrices() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            if (ExpectedConditions.alertIsPresent().apply(driver) != null) {
                System.out.println("LOG: Alert found: " + driver.switchTo().alert().getText());
                driver.switchTo().alert().accept();
            }
        } catch (Exception e) {
        }

        List<WebElement> priceElements = driver.findElements(pageObjects.inventoryItemPrice);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            prices.add(Double.parseDouble(element.getText().replace("$", "")));
        }
        return prices;
    }

    public List<WebElement> getAllProductImages() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(inventoryItemImg));
    }
}