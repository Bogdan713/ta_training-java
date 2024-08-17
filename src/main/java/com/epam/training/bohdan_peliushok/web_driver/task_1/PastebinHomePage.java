package com.epam.training.bohdan_peliushok.web_driver.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class PastebinHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pasteCodeInput = By.id("postform-text");
    private By pasteExpirationDropdown = By.cssSelector("span#select2-postform-expiration-container");
    private By expirationOptionLocator = By.cssSelector("ul.select2-results__options li");
    private By pasteNameInput = By.id("postform-name");
    private By createNewPasteButton = By.xpath("//button[contains(text(),'Create New Paste')]");

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
    }

    public void enterPasteCode(String code) {
        WebElement pasteCode = wait.until(ExpectedConditions.visibilityOfElementLocated(pasteCodeInput));
        pasteCode.sendKeys(code);
    }

    public void selectPasteExpiration(String expiration) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(pasteExpirationDropdown));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(expirationOptionLocator));
        for (WebElement option : driver.findElements(expirationOptionLocator)) {
            if (option.getText().equals(expiration)) {
                option.click();
                break;
            }
        }
    }

    public void enterPasteName(String name) {
        WebElement pasteName = wait.until(ExpectedConditions.visibilityOfElementLocated(pasteNameInput));
        pasteName.sendKeys(name);
    }

    public void createNewPaste() {
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(createNewPasteButton));
        createButton.click();
    }
}
