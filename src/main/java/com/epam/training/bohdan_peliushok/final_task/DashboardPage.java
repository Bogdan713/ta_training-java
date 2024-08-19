package com.epam.training.bohdan_peliushok.final_task;

import org.openqa.selenium.WebDriver;

/**
 * Represents the dashboard page after a successful login.
 */
public class DashboardPage {
    private WebDriver driver;

    /**
     * Constructor to initialize the DashboardPage with WebDriver.
     *
     * @param driver WebDriver instance used to interact with the browser.
     */
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return the title of the page as a String.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
