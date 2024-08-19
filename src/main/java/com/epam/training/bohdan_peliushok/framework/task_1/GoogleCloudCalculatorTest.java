package com.epam.training.bohdan_peliushok.framework.task_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogleCloudCalculatorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    @Test
    public void testEstimateCreation() throws InterruptedException {
        driver.get(PropertyManager.getProperty("url"));

        GoogleCloudCalculatorPage calculatorPage = new GoogleCloudCalculatorPage(driver);
        calculatorPage.addToEstimate();
        calculatorPage.selectComputeEngine();
        calculatorPage.fillForm();
        calculatorPage.clickShare();

        EstimateSummaryPage summaryPage = new EstimateSummaryPage(driver);
        summaryPage.openEstimateSummary();

        String summaryDetails = summaryPage.getSummaryDetails();
        Assert.assertTrue(summaryDetails.contains("Number of Instances\n" + PropertyManager.getProperty("numberOfInstances")));
        Assert.assertTrue(summaryDetails.contains(PropertyManager.getProperty("operatingSystem")));
        Assert.assertTrue(summaryDetails.contains(PropertyManager.getProperty("machineType")));
        Assert.assertTrue(summaryDetails.contains(PropertyManager.getProperty("gpuType")));
        Assert.assertTrue(summaryDetails.contains(PropertyManager.getProperty("localSsd")));
        Assert.assertTrue(summaryDetails.contains(PropertyManager.getProperty("region")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File destFile = new File("screenshots/" + methodName + "_" + timestamp + ".png");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}