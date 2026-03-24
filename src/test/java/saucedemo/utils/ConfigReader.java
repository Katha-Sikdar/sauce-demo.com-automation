package saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        try {

            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Config.properties file not found at src/test/resources/config/");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getInventoryUrl() {
        return properties.getProperty("inventoryUrl");
    }
}