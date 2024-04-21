package com.sanienterprise.dawn.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
