package com.sanienterprise.dawn.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer product_id;
    private String image;
    private String name;
    private String category;
    private String price;
}
