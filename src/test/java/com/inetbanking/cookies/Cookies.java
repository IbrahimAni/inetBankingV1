package com.inetbanking.cookies;

import com.inetbanking.utilities.ReadConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Cookies {
    public static void main(String[] args) {
        ReadConfig readConfig = new ReadConfig();
        String chromeDriverPath = readConfig.getChromePath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.guru99.com/v4/index.php");
        handleCookieConsentModal(driver);

        // Your other actions...

        driver.quit();
    }

    public static void handleCookieConsentModal(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Switch to the iframe
            By iframeSelector = By.cssSelector("#gdpr-consent-notice"); // Replace with actual selector for the iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeSelector));

            // Now interact with elements inside the iframe
            By consentButtonSelector = By.cssSelector("#save"); // Replace with actual XPath for the button
            wait.until(ExpectedConditions.visibilityOfElementLocated(consentButtonSelector));
            wait.until(ExpectedConditions.elementToBeClickable(consentButtonSelector));

            WebElement consentButton = driver.findElement(consentButtonSelector);
            consentButton.click();
            System.out.println("Consent accepted");

            // Switch back to the main content
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Unable to handle consent modal inside iframe: " + e.getMessage());
        }
    }
}
