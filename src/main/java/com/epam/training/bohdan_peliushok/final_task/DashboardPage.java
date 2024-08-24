package com.epam.training.bohdan_peliushok.final_task;

import org.openqa.selenium.WebDriver;

/**
 * This class represents the dashboard page of the application.
 * It provides methods to interact with the elements on the dashboard page.
 */
public class DashboardPage {
    private WebDriver driver;

    /**
     * Constructor to initialize the DashboardPage with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used
     * @throws IllegalArgumentException if the WebDriver instance is null
     */
    public DashboardPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
    }

    /**
     * Gets the title of the current page.
     *
     * @return the page title as a String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}