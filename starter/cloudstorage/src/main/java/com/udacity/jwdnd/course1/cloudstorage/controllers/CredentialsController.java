package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.CredentialForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialService credentialService;
    private AuthenticationService authenticationService;

    @Autowired
    public void setCredentialService(CredentialService credentialService)
    {
        this.credentialService = credentialService;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String addCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Model model, RedirectAttributes redirectAttributes)
    {
        int user = this.authenticationService.currentLoggedInUser().getUserid();
        credentialForm.setUserId(user);
        if(credentialForm.getCredentialid() != null && credentialForm.getCredentialid() > 0)
        {

            this.credentialService.updateCredential(credentialForm);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully updated the credentials!");
        }
        else
        {

            this.credentialService.addCredential(credentialForm);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully added the credentials!");
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteCredential(@PathVariable("id")String id, Model Model, RedirectAttributes redirectAttributes)
    {
        int userId = this.authenticationService.currentLoggedInUser().getUserid();

        Integer cred = Integer.parseInt(id);
        this.credentialService.deleteCredential(cred,userId);
        redirectAttributes.addFlashAttribute("message",
                "You successfully deleted the credentials!");

        return "redirect:/home";
    }

}
