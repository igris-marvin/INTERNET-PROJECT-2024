package com.sanienterprise.dawn.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String contact;
    private String email;
    private String image;
}
