package com.epam.training.bohdan_peliushok.final_task;

import com.epam.training.bohdan_peliushok.common.ConfigManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the login page of the application.
 * It provides methods to interact with the elements on the login page.
 */
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    /**
     * Constructor to initialize the LoginPage with a WebDriver instance.
     * Also initializes web elements using PageFactory and loads configuration properties.
     *
     * @param driver the WebDriver instance to be used
     * @throws IllegalArgumentException if the WebDriver instance is null
     */
    public LoginPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance must not be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
        ConfigManager.loadProperties("final");
    }

    /**
     * Opens the login page using the URL from the configuration properties.
     */
    public void open() {
        driver.get(ConfigManager.getProperty("URL"));
    }

    /**
     * Enters the username into the username input field.
     *
     * @param username the username to be entered
     */
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    /**
     * Enters the password into the password input field.
     *
     * @param password the password to be entered
     */
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Clears the username input field.
     */
    public void clearUsername() {
        clear(usernameInput);
    }

    /**
     * Clears the password input field.
     */
    public void clearPassword() {
        clear(passwordInput);
    }

    /**
     * Clears the specified input field using a series of actions.
     *
     * @param element the WebElement to be cleared
     */
    private void clear(WebElement element) {
        element.clear();
        element.sendKeys(" ");
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    /**
     * Clicks the login button to attempt a login.
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Gets the error message displayed on the login page, if any.
     *
     * @return the error message text, or a default message if no error message is found
     */
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch (NoSuchElementException e) {
            return "No error message found";
        }
    }
}

