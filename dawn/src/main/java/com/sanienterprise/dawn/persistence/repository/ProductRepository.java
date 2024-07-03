package com.sanienterprise.dawn.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanienterprise.dawn.persistence.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
