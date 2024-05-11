package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanienterprise.dawn.api.dto.CategoryCountDTO;
import com.sanienterprise.dawn.api.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService admServ;
    
    @GetMapping("/dashboard")
    public String getAdmin(
        Model model
    ) {
        int prods_count = admServ.getNumberOfProducts();
        int users_count = admServ.getNumberOfUsers();

        CategoryCountDTO category_count_list = admServ.getCategoryCountListObject();

        model.addAttribute("prods_count", prods_count);
        model.addAttribute("users_count", users_count);
        model.addAttribute("category_count_list", category_count_list);

        return "dashboard";
    }

    //create product
    @GetMapping("/create")
    public String getCreate() {
        return "create_product_dash";
    }

    @PostMapping("/create")
    public String posCreate() {
        return "";
    }

    //users

    //products

    //profile

    //register

    //logout

    //search
}
