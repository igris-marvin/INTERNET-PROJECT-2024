package com.sanienterprise.dawn.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAdminDTO {
    private String id_number;
    private String name;
    private String surname;
    private String email;
    private String contact;
    private String username;
    private String password;
}
