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
    private String product_status;

    public ProductDTO(Integer product_id, String product_image, String product_title, String product_category,
            String product_price, String product_quantity, String product_added_date) {
        this.product_id = product_id;
        this.product_image = product_image;
        this.product_title = product_title;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_added_date = product_added_date;
    }

    public ProductDTO(Integer product_id, String product_image, String product_title, String product_category,
            String product_price, String product_status) {
        this.product_id = product_id;
        this.product_image = product_image;
        this.product_title = product_title;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_status = product_status;
    }

    
}
