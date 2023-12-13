package com.inetbanking.testCases;

import com.inetbanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseUrl = readConfig.getApplicationURL();
    public String username = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public  static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br){
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        switch (br) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("Enter the browser you want to run your test in");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        handleGDPRConsentNotice(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException{
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(15);
    }

    public String randomNum(){
        // Random number between 100000 and 999999
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }

    public static void handleGDPRConsentNotice(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Switch to the iframe
            By iframeSelector = By.cssSelector("#gdpr-consent-notice");
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeSelector));

            // Now interact with elements inside the iframe
            By consentButtonSelector = By.cssSelector("#save");
            wait.until(ExpectedConditions.visibilityOfElementLocated(consentButtonSelector));
            wait.until(ExpectedConditions.elementToBeClickable(consentButtonSelector));

            WebElement consentButton = driver.findElement(consentButtonSelector);
            consentButton.click();

            // Switch back to the main content
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            System.out.println("Unable to handle consent modal inside iframe: " + e.getMessage());
        }
    }



}
