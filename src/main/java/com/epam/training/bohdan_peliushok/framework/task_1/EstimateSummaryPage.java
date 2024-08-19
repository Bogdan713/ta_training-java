package com.epam.training.bohdan_peliushok.framework.task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EstimateSummaryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(), 'Open estimate summary')]")
    private WebElement openEstimateSummaryLink;

    @FindBy(xpath = "//h4[contains(text(), 'Cost Estimate Summary')]/ancestor::div[2]")
    private WebElement totalEstimatedCost;

    public EstimateSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openEstimateSummary() {
        wait.until(ExpectedConditions.elementToBeClickable(openEstimateSummaryLink)).click();
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String getSummaryDetails() {
        return wait.until(ExpectedConditions.visibilityOf(totalEstimatedCost)).getText();
    }
}
