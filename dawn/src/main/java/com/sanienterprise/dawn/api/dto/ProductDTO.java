package com.sanienterprise.dawn.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Integer product_id;
    private String product_image;
    private String product_title;
    private String product_category;
    private String product_price;
    private String product_quantity;
    private String product_added_date;
}
