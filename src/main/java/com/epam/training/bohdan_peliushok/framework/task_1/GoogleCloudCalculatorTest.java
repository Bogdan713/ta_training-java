package com.epam.training.bohdan_peliushok.framework.task_1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GoogleCloudCalculatorTest {
    private WebDriver driver;
    private WebDriverManager webDriverManager;

    private String browser;

    public GoogleCloudCalculatorTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ConfigManager.loadProperties(System.getProperty("environment", "test"));
        String[] browsers = ConfigManager.getBrowsers();
        return Arrays.asList(browsers).stream().map(browser -> new Object[]{browser}).toList();
    }
    @Before
    public void setUp() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver(browser);
        driver.get(ConfigManager.getProperty("url"));
    }

    @Test
    public void testEstimateCreation() throws InterruptedException {
        try {
            GoogleCloudCalculatorPage calculatorPage = new GoogleCloudCalculatorPage(driver);
            calculatorPage.addToEstimate();
            calculatorPage.selectComputeEngine();
            calculatorPage.fillForm();
            calculatorPage.closeBanner();
            calculatorPage.waitUntilCostUpdated();

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

        } catch (Exception | Error e) {
            ScreenshotUtil.takeScreenshot(driver, "testEstimateCreation");
            throw e;
        }
    }

    @After
    public void tearDown() {
        //webDriverManager.quitDriver();
    }
}
