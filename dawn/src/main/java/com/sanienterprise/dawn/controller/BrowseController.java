package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/browse")
public class BrowseController {
    
    @GetMapping() 
    public String getBrowse() {
        return "browse";
    }
}
