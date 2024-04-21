package com.sanienterprise.dawn.model;

import java.io.Serializable;

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

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_wishlist_id")
    private Wishlist wishlist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cart_id")
    private Cart cart;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_history_id")
    private History history;

    // -- REFERENCED OBJECT

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Customer customer;

    // --

    public enum AccountStatus {
        ACTIVE, INACTIVE, SUSPENDED, PENDING_ACTIVATION, LOCKED;
    }

    public Account(
        String username, 
        String password, 
        AccountStatus status, 
        Wishlist wishlist, 
        Cart cart,
        History history
    ) {

        this.username = username;
        this.password = password;
        this.status = status;
        this.wishlist = wishlist;
        this.cart = cart;
        this.history = history;
    }
    
}
