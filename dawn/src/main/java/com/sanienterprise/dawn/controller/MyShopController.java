package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class MyShopController {
    
    @GetMapping
    public String getCategory() {
        return "shop";
    }

    @GetMapping("/browse")
    public String getBrowse(
        @RequestParam("category") String category
    ) {
        return "browse";
    }

    @GetMapping("/product")
    public String getProduct(
        @RequestParam("id") Integer id
    ) {
        return "product";
    }
}
