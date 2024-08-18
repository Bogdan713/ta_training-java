package com.epam.training.bohdan_peliushok.web_driver.task_3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By addToEstimateButton = By.xpath("//button//span[contains(text(), 'Add to estimate')]");
    private By computeEngineOption = By.xpath("//h2[contains(text(), 'Compute Engine')]");
    private By numberOfInstancesInput = By.id("c13");
    private By machineTypeDropdown = By.xpath("//span[@id='c35']/ancestor::div[1]");
    private By n1Standard8Option = By.xpath("//span[contains(text(), 'n1-standard-8')]/ancestor::li");
    private By addGPUsButton = By.xpath("//button[contains(@aria-label, 'Add GPUs')]");
    private By gpuTypeDropdown = By.xpath("//*[@id=\"ucj-1\"]/div/div/div/div/div/div/div/div[1]/div/div[2]/div[3]/div[23]/div/div[1]/div/div/div/div[1]/div");
    private By nvidiaV100Option = By.xpath("//span[contains(text(), 'NVIDIA V100')]/ancestor::li");
    private By localSSDDropdown = By.xpath("//span[@id='c43']/ancestor::div[1]");
    private By localSSDOption = By.xpath("//span[contains(text(), '2x375 GB')]/ancestor::li");
    private By regionDropdown = By.xpath("//span[@id='c47']/ancestor::div[1]");
    private By netherlandsRegionOption = By.xpath("//span[contains(text(), 'Netherlands (europe-west4)')]/ancestor::li");
    private By shareButton = By.xpath("//span[contains(text(), 'Share')]");

    public GoogleCloudCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void addToEstimate() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButton));
        addButton.click();
    }

    public void selectComputeEngine() {
        WebElement computeEngine = wait.until(ExpectedConditions.elementToBeClickable(computeEngineOption));
        computeEngine.click();
    }

    public void fillForm() throws InterruptedException {
        WebElement numberOfInstances = wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfInstancesInput));
        numberOfInstances.clear();
        numberOfInstances.sendKeys("4");

        WebElement machineTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(this.machineTypeDropdown));
        machineTypeDropdown.click();
        WebElement n1Standard8 = wait.until(ExpectedConditions.elementToBeClickable(n1Standard8Option));
        n1Standard8.click();

        WebElement addGPUs = wait.until(ExpectedConditions.elementToBeClickable(addGPUsButton));
        addGPUs.click();
        WebElement gpuTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(this.gpuTypeDropdown));
        gpuTypeDropdown.click();
        WebElement nvidiaV100 = wait.until(ExpectedConditions.elementToBeClickable(nvidiaV100Option));
        nvidiaV100.click();

        WebElement localSSDDropdown = wait.until(ExpectedConditions.elementToBeClickable(this.localSSDDropdown));
        localSSDDropdown.click();
        WebElement localSSDOption = wait.until(ExpectedConditions.elementToBeClickable(this.localSSDOption));
        localSSDOption.click();

        WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(this.regionDropdown));
        regionDropdown.click();
        WebElement netherlandsRegion = wait.until(ExpectedConditions.elementToBeClickable(netherlandsRegionOption));
        netherlandsRegion.click();
    }

    public void clickShare() {
        WebElement share = wait.until(ExpectedConditions.elementToBeClickable(shareButton));
        share.click();
    }
}
