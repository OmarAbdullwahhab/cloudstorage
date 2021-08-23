package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    @FindBy(id="inputUsername")
    private WebElement userNameInput;

    @FindBy(id="inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    public LoginForm(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public WebElement getUserNameInput() {
        return userNameInput;
    }

    public void setUserNameInput(WebElement userNameInput) {
        this.userNameInput = userNameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(WebElement passwordInput) {
        this.passwordInput = passwordInput;
    }

    public void fillForm(String user, String password)
    {
        this.userNameInput.sendKeys(user);
        this.passwordInput.sendKeys(password);
    }
    public void doLogin()
    {
        this.submitButton.click();

    }
}
