package com.sanienterprise.dawn.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProductDTO {
    private Integer m_product_id;
    private String m_product_name;
    private String m_product_description;
    private String m_style;
    private Double m_width;
    private Double m_length;
    private Double m_height;
    private Double m_price;
    private Integer m_quantity;
    private String m_category;
    private String m_status;
    private List<ModifyImageDTO> images;
}
