package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sanienterprise.dawn.api.service.CategoryService;
import com.sanienterprise.dawn.persistence.entity.Product;

@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private CategoryService catServ;
    
    public BrowseController(CategoryService catServ) {
        this.catServ = catServ;
    }
    
    @GetMapping("/category")
    public String getBrowseCategory(
        @RequestParam("category") String category,
        Model model
    ) {
        List<Product> categoryProduct = catServ.getProductByCategory(category);

        model.addAttribute("categoryObj", categoryProduct);

        return "browse";
    }
}
