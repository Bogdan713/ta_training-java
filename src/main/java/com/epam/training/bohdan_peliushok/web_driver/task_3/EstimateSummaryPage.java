package com.epam.training.bohdan_peliushok.web_driver.task_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EstimateSummaryPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By openEstimateSummaryLink = By.xpath("//a[contains(text(), 'Open estimate summary')]");
    private By totalEstimatedCost = By.xpath("//h4[contains(text(), 'Cost Estimate Summary')]/ancestor::div[2]");

    public EstimateSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openEstimateSummary() {
        WebElement summaryLink = wait.until(ExpectedConditions.elementToBeClickable(openEstimateSummaryLink));
        summaryLink.click();

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String getSummaryDetails() {
        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(totalEstimatedCost));
        return summary.getText();
    }
}
