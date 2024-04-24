package com.sanienterprise.dawn.controller;

import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.service.ImageService;
import com.sanienterprise.dawn.persistence.entity.Image;

@Controller
@RequestMapping("/query")
public class QueryController {
    
    private ImageService imgServ;

    public QueryController(ImageService imgServ) {
        this.imgServ = imgServ;
    }

    @ModelAttribute(name = "imagename")
    public String getImageName() {
        String imageName = imgServ.getImage(1).getImageName();
        return imageName;
    }

    @ModelAttribute(name = "imagesource")
    public String getImageSource() {
        String imageSource = Base64
                                .getEncoder()
                                .encodeToString(
                                    imgServ
                                        .getImage(1)
                                        .getImage_source()
                                );

        return imageSource;
    }

    @ModelAttribute(name = "image_object")
    public Image getImage() {
        Image image = imgServ.getImage(1);

        return image;
    }
    
    @GetMapping
    public String getQuery() {
        return "query";
    }

    @PostMapping
    public String posQuery(
        @RequestParam(name = "update_name") String image_name,
        @RequestParam(name = "update_image") MultipartFile file,
        @ModelAttribute(name = "image_object") Image image
    ) {
        boolean flag = imgServ.updateImage(image_name, file, image);

        String msg = "Image not updated";

        if(flag) {
            msg = "Image updated!";
        }

        System.out.println("Message: " + msg);

        return "redirect:/display";
    }
}
