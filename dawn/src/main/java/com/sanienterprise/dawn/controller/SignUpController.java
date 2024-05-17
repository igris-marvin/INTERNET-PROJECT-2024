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
        String error_message = "false";
        String message = "";

        model.addAttribute("dto", dto);
        model.addAttribute("error_message", error_message);
        model.addAttribute("message", message);
        
        return "signup";
    }

    @PostMapping
    public String postSignUp(
        @ModelAttribute("dto") SignUpDTO dto,
        @RequestParam("image_file") MultipartFile file,
        @ModelAttribute("error_message") String error_message,
        @ModelAttribute("messages") String message,
        Model model
    ) {
        message = "";
        
        message = sigServ.validatePassword(dto.getPassword(), dto.getC_password());
        message = sigServ.checkUsername(dto.getUsername());
        message = sigServ.checkIdNumber(dto.getId_number());
        
        if(message.length() > 0) {
            error_message = "true";
            
            model.addAttribute("dto", dto);
            model.addAttribute("error_message", error_message);
            model.addAttribute("message", message);
        
            return "signup";
        } else {
            sigServ.createCustomer(dto);
        }

        return "redirect:/signup/success";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }
}
