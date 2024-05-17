package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.dto.SignUpDTO;
import com.sanienterprise.dawn.api.service.SignUpService;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService sigServ;
    
    @GetMapping
    public String getSignUp(
        Model model 
    ) {
        SignUpDTO dto = new SignUpDTO();
        List<String> usernames = sigServ.getAllCustomerUsernames();

        model.addAttribute("dto", dto);
        model.addAttribute("usernames", usernames);

        return "signup";
    }

    @PostMapping
    public String postSignUp(
        @ModelAttribute("dto") SignUpDTO dto,
        @RequestParam("image_file") MultipartFile file,
        Model model
    ) {
        // model = sigServ.createCustomer(dto, file);

        // if() {
        //     return "signup";
        // }

        return "redirect:/signup/success";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }
}
