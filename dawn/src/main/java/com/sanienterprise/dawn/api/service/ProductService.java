package com.sanienterprise.dawn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository prodRepo;

    public ProductService(ProductRepository prodRepo) {
        this.prodRepo = prodRepo;
    }
}
