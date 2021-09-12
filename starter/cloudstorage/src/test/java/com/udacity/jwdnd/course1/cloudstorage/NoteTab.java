package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteTab {


    @FindBy(id="note-id")
    private WebElement noteId;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitButton;

    @FindBy(id="add-note-btn")
    private WebElement addNoteButton;

    @FindBy(className="btn-success")
    private WebElement editNoteButton;

    @FindBy(className="btn-danger")
    private WebElement deleteNoteButton;

    @FindBy(id="nav-notes-tab")
    private WebElement noteTabLink;


    public NoteTab(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public void setAddNoteButton(WebElement addNoteButton) {
        this.addNoteButton = addNoteButton;
    }

    public WebElement getNoteId() {
        return noteId;
    }

    public void setNoteId(WebElement noteId) {
        this.noteId = noteId;
    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(WebElement noteTitle) {
        this.noteTitle = noteTitle;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(WebElement noteDescription) {
        this.noteDescription = noteDescription;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public void fillForm(String id, String title, String desc)
    {
        //this.noteId.sendKeys(id);
        this.noteTitle.clear();
        this.noteDescription.clear();
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(desc);
    }

    public void submitForm(WebDriver driver)
    {

        //$('#noteSubmit').click();
        ((JavascriptExecutor) driver).executeScript("$('#noteSubmit').click();");
    }

    public void openTab()
    {
        this.noteTabLink.click();
    }
    public void showAddDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.id("add-note-btn")).isDisplayed());

         this.addNoteButton.click();
        //WebDriverWait wait = new WebDriverWait(driver,100);
       // wait.until((x) -> x.findElement(By.id("note-id")).isDisplayed());

        //((JavascriptExecutor) driver).executeScript("document.getElementById('ID').style.display='block';");


    }
    public void showEditDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.className("btn-success")).isDisplayed());

        this.editNoteButton.click();
    }

    public void showDeleteDialog(WebDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until((x) -> x.findElement(By.className("btn-danger")).isDisplayed());

        this.deleteNoteButton.click();


    }
}
