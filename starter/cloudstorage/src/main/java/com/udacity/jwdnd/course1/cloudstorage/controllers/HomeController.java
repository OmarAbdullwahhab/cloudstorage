package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

    private AuthenticationService authenticationService;
    private FilesService filesService;
    private NoteService noteService;
    private CredentialService credentialService;


    @Autowired
    public void setNoteService(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }
    @Autowired
    public void setFilesService(FilesService filesService)
    {
        this.filesService = filesService;
    }

    @Autowired
    public void setCredentialService(CredentialService credentialService)
    {
        this.credentialService = credentialService;
    }

    @GetMapping
    public String home(Model model)
    {
        model.addAttribute("currentUser", this.authenticationService.currentLoggedInUser());
        return "home";
    }

    @ModelAttribute(name="files")
    public List<File>  getUploadedFiles(){
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        return  this.filesService.getAllFiles(userId) ;
    }
    @ModelAttribute(name="notes")
    public List<NoteForm>  getNotes(){
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        return  this.noteService.getAllNotes(userId) ;
    }

    @ModelAttribute(name="credentialsList")
    public List<CredentialForm>  getCredentialsList(){
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        return  this.credentialService.getAllCredentials(userId) ;
    }


    @ModelAttribute(name="noteForm")
    public NoteForm noteForm()
    {
        return new NoteForm();
    }

    @ModelAttribute(name="credentialForm")
    public CredentialForm credentialForm()
    {
        return new CredentialForm();
    }





}
