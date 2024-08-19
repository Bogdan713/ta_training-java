package com.epam.training.bohdan_peliushok.final_task;

import org.openqa.selenium.*;

/**
 * Represents the login page of the application.
 */
public class LoginPage {
    private WebDriver driver;
    private By usernameInput = By.xpath("//input[@id='user-name']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//input[@id='login-button']");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    /**
     * Constructor to initialize the LoginPage with WebDriver.
     *
     * @param driver WebDriver instance used to interact with the browser.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Opens the login page URL and disables the autofill feature.
     */
    public void open() {
        driver.get("https://www.saucedemo.com/");
        disableAutofill();
    }

    /**
     * Disables the browser's autofill feature for username and password fields.
     */
    public void disableAutofill() {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('user-name').setAttribute('autocomplete', 'off')");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('password').setAttribute('autocomplete', 'off')");
    }

    /**
     * Enters the specified username into the username input field.
     *
     * @param username the username to enter.
     */
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    /**
     * Enters the specified password into the password input field.
     *
     * @param password the password to enter.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    /**
     * Clears the username input field.
     */
    public void clearUsername() {
        WebElement usernameField = driver.findElement(usernameInput);
        usernameField.clear();
        usernameField.sendKeys(" ");
        usernameField.sendKeys(Keys.CONTROL + "a");
        usernameField.sendKeys(Keys.DELETE);
    }

    /**
     * Clears the password input field.
     */
    public void clearPassword() {
        WebElement passwordField = driver.findElement(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(" ");
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * Retrieves the text of the error message displayed on the page.
     *
     * @return the error message text as a String.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}

