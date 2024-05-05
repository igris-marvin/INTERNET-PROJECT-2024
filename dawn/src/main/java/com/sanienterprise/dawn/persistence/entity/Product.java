package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @Column(nullable = false, length = 100)
    private String product_name;
    
    @Column(nullable = false, length = 1000)
    private String product_description;

    @Column(nullable = true, length = 100)
    private String style;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double width;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double length;
    
    @Column(nullable = true, length = 7, precision = 2)
    private Double height;
    
    @Column(nullable = false, length = 9, precision = 2)
    private Double price;
    
    @Column(nullable = false, length = 7)
    private Integer quantity;
    
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductStatus product_status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;
    
    @Column(nullable = false)
    private Date date_added;

    // - FK

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Image> images;

    public enum Category {
        CHAIR, TABLE, BED, SOFA, CABINET, SHELVE, DESK;
    }

    public Product(
        String product_name, 
        String product_description, 
        String style, 
        Double width, 
        Double length,
        Double height, 
        Double price, 
        Integer quantity, 
        Category category, 
        ProductStatus product_status,
        Date date_added,
        List<Image> images
    ) {
        
        this.product_name = product_name;
        this.product_description = product_description;
        this.style = style;
        this.width = width;
        this.length = length;
        this.height = height;
        this.price = price;
        this.quantity = quantity;
        this.product_status = product_status;
        this.category = category;
        this.date_added = date_added;
        this.images = images;
    }

    public enum ProductStatus {
        AVAILABLE, UNAVAILABLE, OUT_OF_STOCK, COMING_SOON;
    }
}