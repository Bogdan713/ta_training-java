package com.epam.training.bohdan_peliushok.framework.task_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();

    public static void loadProperties(String environment) {
        String propertyFileName = "config-" + environment + ".properties";
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new IOException("Unable to find " + propertyFileName);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + environment, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String[] getBrowsers() {
        String browsers = properties.getProperty("browsers", "chrome");
        return browsers.split(",");
    }
}

