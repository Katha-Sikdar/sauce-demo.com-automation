package saucedemo.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loginToApplication(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.usernameFld)).clear();
        driver.findElement(pageObjects.usernameFld).sendKeys(user);

        driver.findElement(pageObjects.passwordFld).clear();
        driver.findElement(pageObjects.passwordFld).sendKeys(pass);

        driver.findElement(pageObjects.loginBtn).click();
    }

    public void logout() {

        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(pageObjects.menuBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuButton);

        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.logoutLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageObjects.errorMsg)).getText();
    }
}