package com.saucedemo.tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/main/java/SauceDemo/tests/features",

        glue = {"SauceDemo.tests.steps", "SauceDemo.hooks"},

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