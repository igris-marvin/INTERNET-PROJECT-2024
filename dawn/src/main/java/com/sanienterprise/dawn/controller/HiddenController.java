package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.service.HiddenService;
import com.sanienterprise.dawn.persistence.entity.Patron;
import com.sanienterprise.dawn.persistence.entity.Admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/hidden")
public class HiddenController {

    @Autowired
    private HiddenService hidServ;
    
    @GetMapping("/register")
    public String getRegister(
        Model model
    ) {
        Admin admin = new Admin();
        List<String> roles = hidServ.getRoles();

        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);

        return "admin_register";
    }

    @PostMapping("/register")
    public String posRegister(
        @ModelAttribute(name = "admin") Admin admin,
        @RequestParam("role") String role
    ) {

        System.out.println("ADMIN: " + admin.toString());

        return "redirect:/login";
    }
    
}
