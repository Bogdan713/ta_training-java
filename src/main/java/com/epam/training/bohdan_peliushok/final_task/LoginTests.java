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
 * Contains tests for the login functionality of the application.
 */
public class LoginTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private static final Logger log = LogManager.getLogger(LoginTests.class);

    /**
     * Provides test data for parameterized login tests.
     *
     * @return a stream of test data as Object arrays.
     */
    public static Stream<Object[]> data() {
        return Stream.of(new Object[][]{
                {"chrome", "standard_user", "secret_sauce", true, ""},
                {"chrome", "standard_user", "", false, "Password is required"},
                {"chrome", "", "", false, "Username is required"},
                {"edge", "standard_user", "secret_sauce", true, ""},
                {"edge", "standard_user", "", false, "Password is required"},
                {"edge", "", "", false, "Username is required"}
        });
    }

    /**
     * Sets up the WebDriver and LoginPage instance for each test.
     *
     * @param browser the browser to be used for the test.
     */
    public void setUp(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        loginPage = new LoginPage(driver);
    }

    /**
     * Tests the login functionality with various credentials and expected outcomes.
     *
     * @param browser     the browser to be used.
     * @param username    the username to be entered.
     * @param password    the password to be entered.
     * @param shouldBeLogged whether login should be successful.
     * @param errorText   the expected error message if login fails.
     */
    @ParameterizedTest
    @MethodSource("data")
    @DisplayName("Login Test")
    public void testLogin(String browser, String username, String password, Boolean shouldBeLogged, String errorText) {
        setUp(browser);
        loginPage.open();
        log.info("Navigated to the login page");

        loginPage.enterUsername(username.isEmpty() ? "test" : username);
        loginPage.enterPassword(password.isEmpty() ? "pass" : password);
        if (username.isEmpty()) loginPage.clearUsername();
        if (password.isEmpty()) loginPage.clearPassword();
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
     * Cleans up the WebDriver instance after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}