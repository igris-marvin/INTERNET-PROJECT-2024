package com.sanienterprise.dawn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.dto.AccountDTO;
import com.sanienterprise.dawn.api.service.UserService;
import com.sanienterprise.dawn.persistence.entity.Customer;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService useServ;

    //USER ACCOUNT
    @GetMapping("/account")
    public String getAccount(
        Model model
    ) {
        AccountDTO acc = useServ.getUSerDetails();

        System.out.println(acc.toString());

        model.addAttribute("acc", acc);

        return "account";
    }

    @PostMapping("/account")
    public String posAccount(
        @RequestParam("username") String username,
        @RequestParam("name") String name,
        @RequestParam("surname") String surname,
        @RequestParam("email") String email,
        @RequestParam("contact") String contact
    ) {


        return "";
    }
        //UPDATE ACCOUNT DETAILS
        //UPDATE ADDRESS

    //CART
        //ADD TO CART
        //REMOVE FROM CART
        //ORDER

    //REMOVE ACCOUNT

    //
}
