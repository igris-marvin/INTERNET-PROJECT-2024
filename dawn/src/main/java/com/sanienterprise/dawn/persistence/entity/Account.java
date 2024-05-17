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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] profile_image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cart_id")
    private Cart cart;

    // -- REFERENCED OBJECT

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Customer customer;

    public Account(
        String username, 
        String password, 
        byte[] profile_image, 
        Cart cart
    ) {
        this.username = username;
        this.password = password;
        this.profile_image = profile_image;
        this.cart = cart;
    }

    

    // --

    
}
