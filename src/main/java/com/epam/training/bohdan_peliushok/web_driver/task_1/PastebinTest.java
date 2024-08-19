package com.epam.training.bohdan_peliushok.web_driver.task_1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PastebinTest {
    private WebDriver driver;
    private PastebinHomePage pastebinHomePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pastebin.com/");
        pastebinHomePage = new PastebinHomePage(driver);
    }

    @Test
    public void createNewPasteTest() throws InterruptedException {
        pastebinHomePage.enterPasteCode("Hello from WebDriver");
        pastebinHomePage.selectPasteExpiration("10 Minutes");
        pastebinHomePage.enterPasteName("helloweb");
        pastebinHomePage.createNewPaste();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}