package com.sanienterprise.dawn.api.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String name;
    private String surname;
    private String id_number;
    private String email;
    private String contact;
    private String username;
    private String password;
}