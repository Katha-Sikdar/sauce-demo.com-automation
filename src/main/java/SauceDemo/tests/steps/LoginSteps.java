package SauceDemo.tests.steps;

import SauceDemo.base.WebDriverFactory;
import SauceDemo.pages.LoginPage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class LoginSteps {

    private WebDriver driver = WebDriverFactory.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private ObjectMapper mapper = new ObjectMapper();

    @Given("I am on the Sauce Demo login page")
    public void iAmOnTheSauceDemoLoginPage() {
    }

    @When("I login using {string} credentials from json")
    public void LoginUsingCredentials(String userKey) throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/fixtures/testdata.json";
        File jsonFile = new File(filePath);

        if (!jsonFile.exists()) {
            throw new RuntimeException("JSON file not found at: " + jsonFile.getAbsolutePath());
        }

        JsonNode rootNode = mapper.readTree(jsonFile);
        String usernameValue = rootNode.path(userKey).path("username").asText();
        String passwordValue = rootNode.path(userKey).path("password").asText();

        loginPage.loginToApplication(usernameValue, passwordValue);
    }

    @Then("I should be redirected to the products inventory page")
    public void RedirectedToTheProductsInventoryPage() {
        Assert.assertTrue("Redirect failed!", driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("I should see the products inventory page")
    public void verifyInventoryPage() {
        Assert.assertTrue("Redirect failed!", driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("I should see an error message containing {string}")
    public void verifyErrorMessage(String expectedText) {
        Assert.assertTrue("Error mismatch!", loginPage.getErrorMessage().contains(expectedText));
    }

    @And("I logout from the application")
    public void performLogout() {
        loginPage.logout();
    }

    @Then("I should be redirected back to the login page")
    public void verifyLoginPageRedirect() throws InterruptedException {
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Logout failed! Still on: " + currentUrl,
                currentUrl.equals("https://www.saucedemo.com/") || currentUrl.equals("https://www.saucedemo.com"));
    }

    @And("I should not be able to access the inventory page directly")
    public void verifySessionPersistence() {
        driver.get("https://www.saucedemo.com/inventory.html");
        Assert.assertTrue("Direct access should be denied!", loginPage.getErrorMessage().contains("You can only access"));
    }

    @Then("I should see an error message {string}")
    public void verifySpecificErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals("The error message for locked-out user is incorrect!",
                expectedErrorMessage,
                actualErrorMessage);
    }
}