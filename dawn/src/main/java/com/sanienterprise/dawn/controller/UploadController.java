package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.service.ImageService;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private ImageService imgServ;
    
    public UploadController(ImageService imgServ) {
        this.imgServ = imgServ;
    }

    @GetMapping
    public String getUpload() {

        return "upload";
    }

    @PostMapping
    public String postUpload(
        @RequestParam("upload_image") MultipartFile file
    ) {

        System.out.println("Upload Image");
        System.out.println("Image Name: " + file.getContentType());

        boolean flag = imgServ.uploadImage(file);

        if(flag) {
            System.out.println("Image Uploaded");
        } else {
            System.out.println("Image Not Upload");
        }

        return "home";
    }
}
