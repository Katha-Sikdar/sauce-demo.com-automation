package saucedemo.tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/main/java/saucedemo/tests/features",

        glue = {"saucedemo.tests.steps", "saucedemo.hooks"},

        tags = "@Positive or @Negative or @Session",

        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },

        monochrome = true
)
public class TestRunner {

}