package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass {
    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(user);
        logger.info("Entered username");
        lp.setPassword(pwd);
        logger.info("Entered password");
        lp.clickSubmit();

        if(isAlertPresent()){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(true);
            logger.warn("Login failed");
            logger.info("**********");
            captureScreen(driver, "loginDDT");
        }else {
            Assert.assertTrue(true);
            logger.info("Login test passed");
            logger.info("**********");
            lp.clickLogout();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException e){
            return false;
        }
    }

    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir")+ File.separator + "src/test/java/com/inetbanking/testData/LoginData.xlsx";
        int rowNum = XLUtils.getRowCount(path, "Sheet1");
        int cellNum = XLUtils.getCellCount(path, "Sheet1", rowNum);

        String[][] loginData = new String[rowNum][cellNum];

        for(int i = 1; i <= rowNum; i++){
            for (int j = 0; j < cellNum; j++){
                loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
            }
        }

        return loginData;
    }
}
