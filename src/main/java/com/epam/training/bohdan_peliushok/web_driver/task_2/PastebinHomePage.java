package com.epam.training.bohdan_peliushok.web_driver.task_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PastebinHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pasteCodeInput = By.id("postform-text");
    private By pasteExpirationDropdown = By.cssSelector("span#select2-postform-expiration-container");
    private By expirationOptionLocator = By.cssSelector("ul.select2-results__options li");
    private By syntaxHighlightingDropdown = By.cssSelector("span#select2-postform-format-container");
    private By nestedSyntaxList = By.cssSelector("#select2-postform-format-results > li:nth-child(2) > ul");
    private By pasteNameInput = By.id("postform-name");
    private By createNewPasteButton = By.xpath("//button[contains(text(),'Create New Paste')]");
    private By rawCodePreTag = By.tagName("pre");
    private By pageTitleHeader = By.cssSelector("div.info-top > h1");
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

    public void selectSyntaxHighlighting(String syntax) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(syntaxHighlightingDropdown));
        dropdown.click();

        WebElement nestedList = wait.until(ExpectedConditions.visibilityOfElementLocated(nestedSyntaxList));
        for (WebElement option : nestedList.findElements(By.tagName("li"))) {
            if (option.getText().equals(syntax)) {
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

    public String getPageTitle() {
        WebElement titleHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitleHeader));
        return titleHeader.getText();
    }

    public String getRawPasteContent() {
        String currentUrl = driver.getCurrentUrl();
        String rawUrl = currentUrl.replace("pastebin.com/", "pastebin.com/raw/");
        driver.navigate().to(rawUrl);

        WebElement preTag = wait.until(ExpectedConditions.visibilityOfElementLocated(rawCodePreTag));
        return preTag.getText();
    }
}