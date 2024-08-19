package com.epam.training.bohdan_peliushok.final_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class for creating WebDriver instances based on the browser type.
 */
public class WebDriverFactory {

    /**
     * Creates a WebDriver instance for the specified browser.
     *
     * @param browser the browser for which to create a WebDriver instance.
     * @return a WebDriver instance for the specified browser.
     * @throws IllegalArgumentException if the specified browser is not supported.
     */
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
