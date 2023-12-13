package com.inetbanking.testCases;

import com.inetbanking.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class TC_AddCustomerTest_003 extends BaseClass{
    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        logger.info("Logging in");
        lp.setUserName(username);
        lp.setPassword(password);
        lp.clickSubmit();
        logger.info("User logged in");

        String cName = randomString();


        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        logger.info("Click on the add new customer btn");
        addCustomerPage.clickAddNewCustomer();
        logger.info("Enter customer name: " + cName);
        addCustomerPage.custName(cName+" Test");
        logger.info("Enter customer gender");
        addCustomerPage.custGender("Male");
        logger.info("Enter customer dob");
        addCustomerPage.custDOB("05", "31", "99");
        logger.info("Enter customer address");
        addCustomerPage.custAddress("United Kingdom");
        logger.info("Enter customer city");
        addCustomerPage.custCity("Salford");
        logger.info("Enter customer state");
        addCustomerPage.custState("Manchester");
        logger.info("Enter customer pin code");
        addCustomerPage.custPinno(randomNum());
        logger.info("Enter customer telephone number");
        addCustomerPage.custTelephoneno("07517181746");
        logger.info("Enter customer email");
        addCustomerPage.custEmail(cName+"@cdsdev.uk");
        logger.info("Enter customer password");
        addCustomerPage.custPwd("Ibtxtpwd31$");
        logger.info("Click submit btn");
        addCustomerPage.clickSubmitBtn();

        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

        System.out.println(driver.getTitle());

        if(res){
            Assert.assertTrue(true, "Expected text not found on the page!");
            logger.info("Customer Registered Successfully!!!");
        }else{
            captureScreen(driver, "addCustomer");
            logger.error("Error registering customer");
        }
    }
}
