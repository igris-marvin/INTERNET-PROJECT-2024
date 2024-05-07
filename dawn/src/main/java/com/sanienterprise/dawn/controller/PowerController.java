package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/power")
public class PowerController { //same as servlet
    
    @GetMapping
    public String getPower() {
        return "power";
    }

    @PostMapping
    public String postPower(
        @RequestParam("name") String name, 
        @RequestParam("surname") String surname
    ) {
        System.out.println("name: " + name + "\nsurname: " + surname);

        return "redirect:/";
    }
}
