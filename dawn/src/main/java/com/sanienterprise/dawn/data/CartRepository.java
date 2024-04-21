package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
