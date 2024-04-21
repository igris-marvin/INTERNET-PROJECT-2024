package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;
    
    @Column(nullable = false, length = 50)
    private String street;
    
    @Column(nullable = false, length = 50)
    private String surburb;
    
    @Column(nullable = false, length = 50)
    private String city;
    
    @Column(nullable = false, length = 50)
    private String province;
    
    @Column(nullable = false, length = 4)
    private String zip_code;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    private Customer customer;

    public Address(
        String street, 
        String surburb, 
        String city, 
        String province, 
        String zip_code
    ) {

        this.street = street;
        this.surburb = surburb;
        this.city = city;
        this.province = province;
        this.zip_code = zip_code;
    }

    
}
