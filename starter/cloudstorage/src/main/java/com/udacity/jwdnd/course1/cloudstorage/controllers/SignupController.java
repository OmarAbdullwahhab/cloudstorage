package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(SignupForm signupForm,Model model) {

        return "signup";
    }

    @PostMapping()
    public String signupUser(SignupForm signupForm, Model model) {
        String signupError = null;

        if (!userService.isUsernameAvailable(signupForm.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(signupForm);
            if (rowsAdded < 1) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}