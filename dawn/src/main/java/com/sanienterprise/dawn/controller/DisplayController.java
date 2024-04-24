package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.service.DisplayService;

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
        Model model,
        @RequestParam("id") Integer id
    ) {
        if(id == null) {
            id = 1;
        }

        String image = dispServ.getImage(id);

        System.out.println("Image: " + image);

        model.addAttribute("image", image);

        return "display";
    }

    @PostMapping
    public String postDisplay() {

        return "display";
    }
}
