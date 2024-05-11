package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanienterprise.dawn.api.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService admServ;
    
    @GetMapping("/dashboard")
    public String getAdmin() {
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
