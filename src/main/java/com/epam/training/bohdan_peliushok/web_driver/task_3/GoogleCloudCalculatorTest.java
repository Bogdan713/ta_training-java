package com.epam.training.bohdan_peliushok.web_driver.task_3;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleCloudCalculatorTest {
    @Test
    public void testEstimateCreation() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://cloud.google.com/products/calculator");

            GoogleCloudCalculatorPage calculatorPage = new GoogleCloudCalculatorPage(driver);
            calculatorPage.addToEstimate();
            calculatorPage.selectComputeEngine();
            calculatorPage.fillForm();
            calculatorPage.clickShare();

            EstimateSummaryPage summaryPage = new EstimateSummaryPage(driver);
            summaryPage.openEstimateSummary();

            String summaryDetails = summaryPage.getSummaryDetails();
            Assert.assertTrue(summaryDetails.contains("Number of Instances\n" + "4"));
            Assert.assertTrue(summaryDetails.contains("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL"));
            Assert.assertTrue(summaryDetails.contains("Regular"));
            Assert.assertTrue(summaryDetails.contains("n1-standard-8"));
            Assert.assertTrue(summaryDetails.contains("NVIDIA V100"));
            Assert.assertTrue(summaryDetails.contains("2x375 GB"));
            Assert.assertTrue(summaryDetails.contains("Netherlands (europe-west4)"));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
