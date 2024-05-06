package com.sanienterprise.dawn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //user account
    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }
}
