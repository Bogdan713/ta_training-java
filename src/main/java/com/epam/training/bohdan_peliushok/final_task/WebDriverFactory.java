package com.epam.training.bohdan_peliushok.final_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class to create WebDriver instances based on the specified browser type.
 */
public class WebDriverFactory {

    /**
     * Creates a WebDriver instance for the specified browser.
     *
     * @param browser the name of the browser to create the WebDriver for
     * @return the created WebDriver instance
     * @throws IllegalArgumentException if the browser name is null, empty, or not supported
     */
    public static WebDriver createDriver(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            throw new IllegalArgumentException("The browser name cannot be null or empty.");
        }

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Browser not supported");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
