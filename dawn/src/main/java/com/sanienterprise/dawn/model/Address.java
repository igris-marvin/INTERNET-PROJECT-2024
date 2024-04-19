package com.sanienterprise.dawn.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;
    
    @Column(nullable = false, length = 50)
    private String street;
    
    @Column(nullable = false, length = 50)
    private String city;
    
    @Column(nullable = false, length = 50)
    private String province;
    
    @Column(nullable = false, length = 50)
    private String surburb;
    
    @Column(nullable = false, length = 4)
    private Integer zip_code;
}
