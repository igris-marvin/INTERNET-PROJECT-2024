package com.sanienterprise.dawn.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyImageDTO implements Serializable {
    private Integer image_id;
    private String image_data;
}