package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/files")
public class FilesController {


    private FilesService filesService;
    private AuthenticationService authenticationService;

    @Autowired
    public void setFilesService(FilesService filesService)
    {
        this.filesService = filesService;
    }
    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }




    @PostMapping
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        filesService.AddFile(file,userId);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable("id")String id,RedirectAttributes redirectAttributes)
    {
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        Integer file = Integer.parseInt(id);
        this.filesService.deleteFile(file,userId);
        redirectAttributes.addFlashAttribute("message",
                "You successfully deleted the file!");

        return "redirect:/home";
    }

    @GetMapping("/view/{id}")
    public void viewFile(@PathVariable("id")String id, HttpServletResponse response)
    {
        int userId = this.authenticationService.currentLoggedInUser().getUserid();
        Integer file = Integer.parseInt(id);
        File found = this.filesService.getFile(file,userId);
        if(found!=null)
        {
            try {
                response.setContentType(found.getContentType());
                response.setContentLength(Integer.parseInt(found.getFileSize()));
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + found.getFileName());
                response.getOutputStream().write(found.getFileData());


            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }






}
