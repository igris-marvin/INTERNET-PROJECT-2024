package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.service.DisplayService;
import com.sanienterprise.dawn.persistence.entity.Image;

@Controller
@RequestMapping("/display")
public class DisplayController {

    @Autowired
    private DisplayService dispServ;

    public DisplayController(DisplayService dispServ) {
        this.dispServ = dispServ;
    }

    @GetMapping
    public String getDisplay(
        Model model
    ) {

        List<String> images = dispServ.getAllImages();

        for(String image: images) {
            System.out.println("Images " + image);
        }

        model.addAttribute("images", images);

        return "display";
    }

    @PostMapping
    public String postDisplay() {

        return "display";
    }
}
