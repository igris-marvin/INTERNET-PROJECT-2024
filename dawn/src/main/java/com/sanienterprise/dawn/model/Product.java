package com.sanienterprise.dawn.model;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @Column(nullable = false, length = 100)
    private String product_name;
    
    @Column(nullable = false, length = 1000)
    private String product_description;
    
    @Column(nullable = false, length = 9, precision = 2)
    private Double price;
    
    @Column(nullable = false, length = 7)
    private Integer quantity;
    
    @Column(nullable = false, length = 10)
    private String status;
    
    @Column(nullable = false)
    private Date date_added;
    
    @ManyToOne
    @Column(nullable = false)
    private Category category;
}