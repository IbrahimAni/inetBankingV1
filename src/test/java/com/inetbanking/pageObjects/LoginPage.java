package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver local_driver;

    public LoginPage(WebDriver remote_driver){
        local_driver = remote_driver;
        PageFactory.initElements(remote_driver, this);
    }

    @FindBy(name = "uid")
    @CacheLookup
    WebElement txtUserName;

    @FindBy(name = "password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(xpath = "//*[@id=\"save\"]/span[1]/div")
    @CacheLookup
    WebElement accept;

    @FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
    @CacheLookup
    WebElement linkLogout;

    public void setUserName(String uname){
        txtUserName.sendKeys(uname);
    }

    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }

    public void acceptCookies(){
        accept.click();
    }

    public void clickSubmit() {
        btnLogin.click();
    }

    public void clickLogout(){
        linkLogout.click();
    }
}
