package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NoteForm {


    @FindBy(id="note-id")
    private WebElement noteId;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitButton;

    public NoteForm(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
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
        this.noteId.sendKeys(id);
        this.noteTitle.sendKeys(title);
        this.noteDescription.sendKeys(desc);
    }

    public void submitForm()
    {
        this.submitButton.click();
    }

}
