package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialTab {


    @FindBy(id="credential-id")
    private WebElement credentialId;

    @FindBy(id="credential-url")
    private WebElement credentialUrl;

    @FindBy(id="credential-username")
    private WebElement credentialUsername;

    @FindBy(id="credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement submitButton;

    @FindBy(id="add-cred-btn")
    private WebElement addCredentialButton;

    @FindBy(className="editBtn")
    private WebElement editCredentialButton;

    @FindBy(className="deleteBtn")
    private WebElement deleteCredentialButton;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialTabLink;


    public CredentialTab(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public WebElement getCredentialPassword() {
        return credentialPassword;
    }

    public void setCredentialPassword(WebElement credentialPassword) {
        this.credentialPassword = credentialPassword;
    }


    public WebElement getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(WebElement credentialId) {
        this.credentialId = credentialId;
    }

    public WebElement getCredentialUrl() {
        return credentialUrl;
    }

    public void setCredentialUrl(WebElement credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public WebElement getCredentialUsername() {
        return credentialUsername;
    }

    public void setCredentialUsername(WebElement credentialUsername) {
        this.credentialUsername = credentialUsername;
    }

    public WebElement getAddCredentialButton() {
        return addCredentialButton;
    }

    public void setAddCredentialButton(WebElement addCredentialButton) {
        this.addCredentialButton = addCredentialButton;
    }



    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public void fillForm(String id,String url, String user,  String password)
    {

        this.credentialUrl.clear();
        this.credentialUsername.clear();
        this.credentialPassword.clear();

        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(user);
        this.credentialPassword.sendKeys(password);
    }

    public void submitForm(WebDriver driver)
    {
        ((JavascriptExecutor) driver).executeScript("$('#credentialSubmit').click();");
    }

    public void openTab()
    {
        this.credentialTabLink.click();
    }
    public void showAddDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("add-cred-btn")).isDisplayed());

        this.addCredentialButton.click();



    }
    public void showEditDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.className("editBtn")).isDisplayed());
        this.editCredentialButton.click();
    }

    public void showDeleteDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.className("deleteBtn")).isDisplayed());
        this.deleteCredentialButton.click();


    }
}
