package com.epam.training.bohdan_peliushok.final_task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This class contains test cases for testing the login functionality of the application.
 */
public class LoginTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final Logger log = LogManager.getLogger(LoginTests.class);

    /**
     * Provides test data for the parameterized login tests.
     *
     * @return a stream of test data arrays
     */
    public static Stream<Object[]> data() {
        return Stream.of(new Object[][]{
                {"chrome", "standard_user", false, "secret_sauce", false, true, ""},
                {"chrome", "standard_user", false, "any", true, false, "Password is required"},
                {"chrome", "any", true, "any", true, false, "Username is required"},
                {"edge", "standard_user", false, "secret_sauce", false, true, ""},
                {"edge", "standard_user", false, "any", true, false, "Password is required"},
                {"edge", "any", true, "any", true, false, "Username is required"},
        });
    }

    /**
     * Parameterized test for verifying the login functionality with various inputs.
     *
     * @param browser        the browser to be used for the test
     * @param username       the username to be entered
     * @param clearUsername  whether to clear the username field
     * @param password       the password to be entered
     * @param clearPassword  whether to clear the password field
     * @param shouldBeLogged whether the login should be successful
     * @param errorText      the expected error message text if the login fails
     */
    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("Login Test")
    public void testLogin(String browser, String username, Boolean clearUsername, String password, Boolean clearPassword, Boolean shouldBeLogged, String errorText) {
        driver = WebDriverFactory.createDriver(browser);
        loginPage = new LoginPage(driver);
        loginPage.open();
        log.info("Navigated to the login page");

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        if (clearUsername) loginPage.clearUsername();
        if (clearPassword) loginPage.clearPassword();
        loginPage.clickLoginButton();

        if (shouldBeLogged) {
            DashboardPage dashboardPage = new DashboardPage(driver);
            String pageTitle = dashboardPage.getPageTitle();
            assertThat("Page title should be 'Swag Labs'", pageTitle, is("Swag Labs"));
            log.info("Successfully logged in with username: " + username);
        } else {
            String errorMessage = loginPage.getErrorMessage();
            assertThat("Error message should contain the expected text", errorMessage, containsString(errorText));
            log.info("Verified error message '" + errorText + "'");
        }
    }

    /**
     * Cleans up after each test by quitting the WebDriver instance.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}