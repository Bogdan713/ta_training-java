package com.epam.training.bohdan_peliushok.web_driver.task_2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PastebinTest {

    private WebDriver driver;
    private PastebinHomePage pastebinHomePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pastebin.com/");
        pastebinHomePage = new PastebinHomePage(driver);
    }

    @Test
    public void createNewPasteTest() throws InterruptedException {
        String pasteCode = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        pastebinHomePage.enterPasteCode(pasteCode);

        pastebinHomePage.selectSyntaxHighlighting("Bash");

        pastebinHomePage.selectPasteExpiration("10 Minutes");

        String pasteName = "how to gain dominance among developers";
        pastebinHomePage.enterPasteName(pasteName);

        pastebinHomePage.createNewPaste();

        assertEquals(pasteName, pastebinHomePage.getPageTitle());

        WebElement syntaxElement = driver.findElement(By.xpath("//a[contains(@href, 'bash')]"));
        assertTrue(syntaxElement.isDisplayed(), "Syntax highlighting for Bash is not applied");

        assertEquals(pasteCode, pastebinHomePage.getRawPasteContent(), "The code content does not match");
        Thread.sleep(3000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}