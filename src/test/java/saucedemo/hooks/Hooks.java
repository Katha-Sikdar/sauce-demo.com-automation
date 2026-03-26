package saucedemo.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import saucedemo.base.WebDriverFactory;
import saucedemo.utils.ConfigReader;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;
    private ConfigReader configReader;

    @Before
    public void setUp() {
        configReader = new ConfigReader();
        driver = WebDriverFactory.initializeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(configReader.getBaseUrl());
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Scenario_Screenshot");
            } catch (Exception e) {
                System.err.println("Error capturing screenshot: " + e.getMessage());
            }
        }

        WebDriverFactory.quitDriver();
    }
}