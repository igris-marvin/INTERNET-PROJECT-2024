package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_id;

    private Double total;
    private int numOfProducts;

    // List<Product> prod_id
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    // -- REFERENCED OBJECT

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cart")
    private Account account;

    public Cart() {
        total = 0.0;
        numOfProducts = 0;
        products = new ArrayList<>();
    }

    public Cart(Double total, int numOfProducts, List<Product> products) {
        this.total = total;
        this.numOfProducts = numOfProducts;
        this.products = products;
    }

    // --


}
