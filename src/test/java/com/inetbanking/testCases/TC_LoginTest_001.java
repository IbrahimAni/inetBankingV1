package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC_LoginTest_001 extends BaseClass {
    @Test
    public void loginTest() throws IOException {
        try {
            logger.info("URL is opened");
            LoginPage lp = new LoginPage(driver);
            lp.setUserName(username);
            logger.info("Entered username");
            lp.setPassword(password);
            logger.info("Entered password");
            lp.clickSubmit();

            // This assertion might throw an AssertionError if the titles don't match
            Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
            logger.info("Login test passed");
        } catch (AssertionError e) {
            captureScreen(driver, "loginTest");
            logger.error("Login test failed", e);
            throw e; // Rethrow the exception to ensure the test is marked as failed
        }
    }
}
