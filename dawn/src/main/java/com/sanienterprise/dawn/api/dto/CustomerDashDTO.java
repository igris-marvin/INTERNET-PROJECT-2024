package com.sanienterprise.dawn.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDashDTO implements Serializable {
    private Integer user_id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String date_added;
}
