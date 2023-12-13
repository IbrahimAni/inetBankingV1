package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver){
        this.ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div/ul/li[2]/a")
    @CacheLookup
    WebElement linkAddNewCustomer;

    @FindBy(how = How.NAME, using = "name")
    @CacheLookup
    WebElement txtCustomerName;

    @FindBy(how = How.NAME, using = "rad1")
    @CacheLookup
    WebElement rdGender;

    @FindBy(how = How.ID_OR_NAME, using = "dob")
    @CacheLookup
    WebElement txtDob;

    @FindBy(how = How.NAME, using = "addr")
    @CacheLookup
    WebElement txtAddress;

    @FindBy(how = How.NAME, using = "city")
    @CacheLookup
    WebElement txtCity;

    @FindBy(how = How.NAME, using = "state")
    @CacheLookup
    WebElement txtState;

    @FindBy(how = How.NAME, using = "pinno")
    @CacheLookup
    WebElement txtPinno;

    @FindBy(how = How.NAME, using = "telephoneno")
    @CacheLookup
    WebElement txtTelephoneNo;

    @FindBy(how = How.NAME, using = "emailid")
    @CacheLookup
    WebElement txtEmailId;

    @FindBy(how = How.NAME, using = "password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(how = How.NAME, using = "sub")
    @CacheLookup
    WebElement btnSubmit;

    public void clickAddNewCustomer(){
        linkAddNewCustomer.click();
    }

    public void custName(String cName){
        txtCustomerName.sendKeys(cName);
    }

    public void custGender(String cGender){
        rdGender.click();
    }

    public void custDOB(String mm, String dd, String yy){
        txtDob.sendKeys(mm);
        txtDob.sendKeys(dd);
        txtDob.sendKeys(yy);
    }

    public void custAddress(String cAddress){
        txtAddress.sendKeys(cAddress);
    }

    public void custCity(String cCity) {
        txtCity.sendKeys(cCity);
    }
    public void custState(String cState) {
        txtState.sendKeys(cState);
    }

    public void custPinno(String cPinno){
        txtPinno.sendKeys(cPinno);
    }

    public void custTelephoneno(String cTelephoneno){
        txtTelephoneNo.sendKeys(cTelephoneno);
    }

    public void custEmail(String cEmail){
        txtEmailId.sendKeys(cEmail);
    }

    public void custPwd(String cPwd){
        txtPassword.sendKeys(cPwd);
    }

    public void clickSubmitBtn(){
        btnSubmit.click();
    }
}
