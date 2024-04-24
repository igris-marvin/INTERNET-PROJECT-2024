package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sanienterprise.dawn.api.service.UserService;
@Controller
@RequestMapping("/user")
public class SignUpController {

    @Autowired
    private UserService userServ;

    public SignUpController(UserService userServ) {
        this.userServ = userServ;
    }

    // GET SIGN UP
    @GetMapping("/signup")
    public String getSignUp() {

        return "signup";
    }

    // POST SIGN UP
    @PostMapping("/signup")
    public String postSignUp() {

        userServ.addAdminAndCustomer();

        return "redirect:/";
    }
}
