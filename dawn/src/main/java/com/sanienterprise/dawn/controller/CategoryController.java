package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/category")
@SessionAttributes("category_type")
public class CategoryController {

    @GetMapping
    public String getCategory() {
        return "shop";
    }
    
}