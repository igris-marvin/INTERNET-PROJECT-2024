package com.sanienterprise.dawn.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    private String product_name;
    private String product_description;
    private String style;
    private Double width;
    private Double length;
    private Double height;
    private Double price;
    private Integer quantity;
    private String c_category;
    private String s_status;
}
