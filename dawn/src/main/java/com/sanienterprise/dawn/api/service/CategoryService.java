package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.entity.Product.Category;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class CategoryService {
    
    @Autowired
    private ProductRepository prodRepo;

    public CategoryService(ProductRepository prodRepo) {
        this.prodRepo = prodRepo;
    }

    public List<Product> getProductByCategory(String category) {

        List<Product> catProd = prodRepo.findAll();

        List<Product> catProdData = new ArrayList<>();

        for(Product x: catProd) {

            if(x.getCategory().name().equalsIgnoreCase(category)) { //filter to chosen category
                catProdData.add(x);
            }
        }

        return catProdData;
    }
}
