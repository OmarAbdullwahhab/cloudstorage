package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.NoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notes")
public class NotesController {


    private NoteService noteService;
    private AuthenticationService authenticationService;

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

    @PostMapping
    public String addNote(@ModelAttribute("noteForm") NoteForm noteForm,Model model, RedirectAttributes redirectAttributes)
    {
        int user = this.authenticationService.currentLoggedInUser().getUserid();
        noteForm.setUserId(user);
        if(noteForm.getNoteid() != null && noteForm.getNoteid() > 0)
        {
            this.noteService.updateNote(noteForm);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully updated the note!");
        }
        else
        {
            this.noteService.addNote(noteForm);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully added the note!");
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id")String id,Model Model, RedirectAttributes redirectAttributes)
    {
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        Integer note = Integer.parseInt(id);
        this.noteService.deleteNote(note,userId);
        redirectAttributes.addFlashAttribute("message",
                "You successfully deleted the note!");

        return "redirect:/home";
    }


}
