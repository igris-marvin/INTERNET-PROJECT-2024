package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
