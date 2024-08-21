package com.epam.training.bohdan_peliushok.framework.task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Lines to be changed: Using @FindBy for element locators
    @FindBy(xpath = "//button//span[contains(text(), 'Add to estimate')]")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//h2[contains(text(), 'Compute Engine')]")
    private WebElement computeEngineOption;

    @FindBy(id = "c13")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//span[@id='c35']/ancestor::div[1]")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//span[contains(text(), 'n1-standard-8')]/ancestor::li")
    private WebElement n1Standard8Option;

    @FindBy(xpath = "//button[contains(@aria-label, 'Add GPUs')]")
    private WebElement addGPUsButton;

    @FindBy(xpath = "//*[@id='ucj-1']/div/div/div/div/div/div/div/div[1]/div/div[2]/div[3]/div[23]/div/div[1]/div/div/div/div[1]/div")
    private WebElement gpuTypeDropdown;

    @FindBy(xpath = "//span[contains(text(), 'NVIDIA V100')]/ancestor::li")
    private WebElement nvidiaV100Option;

    @FindBy(xpath = "//span[@id='c43']/ancestor::div[1]")
    private WebElement localSSDDropdown;

    @FindBy(xpath = "//span[contains(text(), '2x375 GB')]/ancestor::li")
    private WebElement localSSDOption;

    @FindBy(xpath = "//span[@id='c47']/ancestor::div[1]")
    private WebElement regionDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Netherlands (europe-west4)')]/ancestor::li")
    private WebElement netherlandsRegionOption;

    @FindBy(xpath = "//span[contains(text(), 'Share')]")
    private WebElement shareButton;

    @FindBy(xpath = "//span[@class='close']")
    private WebElement bannerCloseButton;

    public GoogleCloudCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addToEstimate() {
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButton)).click();
    }

    public void selectComputeEngine() {
        wait.until(ExpectedConditions.elementToBeClickable(computeEngineOption)).click();
    }

    public void fillForm() {
        wait.until(ExpectedConditions.visibilityOf(numberOfInstancesInput)).clear();
        numberOfInstancesInput.sendKeys("4");

        wait.until(ExpectedConditions.elementToBeClickable(machineTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(n1Standard8Option)).click();

        wait.until(ExpectedConditions.elementToBeClickable(addGPUsButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(gpuTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(nvidiaV100Option)).click();

        wait.until(ExpectedConditions.elementToBeClickable(localSSDDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(localSSDOption)).click();

        wait.until(ExpectedConditions.elementToBeClickable(regionDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(netherlandsRegionOption)).click();
    }

    public void clickShare() {
        wait.until(ExpectedConditions.elementToBeClickable(shareButton)).click();
    }

    public void waitUntilCostUpdated() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Service cost updated']")));
    }

    public void closeBanner() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(bannerCloseButton)).click();
        } catch (NoSuchElementException e) {
            System.out.println("Banner close button not found. Skipping...");
        }
    }
}
