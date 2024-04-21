package com.sanienterprise.dawn.persistence.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@NoArgsConstructor
public class Wishlist implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlist_id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product_id;

    // -- REFERENCING OBJECT
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "wishlist")
    private Account account;

    public Wishlist(List<Product> product_id) {
        this.product_id = product_id;
    }
}
