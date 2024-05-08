package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.service.PatronService;
import com.sanienterprise.dawn.persistence.entity.Patron;

@Controller
@RequestMapping("/home")
public class GuestController {
    
    private PatronService patServ;
    private PasswordEncoder passEnc;

    //LOGIN
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    
    //SIGNUP
    @GetMapping("/signup")
    public String getSignUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String setSignUp(
        @RequestParam("username") String username,
        @RequestParam("password") String password
    ) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        String encoded = passEnc.encode(password);

        System.out.println("encoded password: " + encoded);

        patServ.addUser(username, encoded);

        return "redirect:/user/account";
    }

    //BROWSER
}
