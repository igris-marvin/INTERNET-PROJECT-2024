package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    
    //user signup
    @GetMapping("/signup")
    public String getSignUp() {
        return "signup";
    }

    //user login
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //user account
    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }
}
