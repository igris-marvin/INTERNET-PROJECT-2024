package com.sanienterprise.dawn.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String contact;
}