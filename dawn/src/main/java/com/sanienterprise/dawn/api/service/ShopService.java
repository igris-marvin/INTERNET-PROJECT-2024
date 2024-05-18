package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class ShopService {
    
    @Autowired
    private ProductRepository proRepo;

    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> prods = proRepo.findAll();

        List<ProductDTO> dtos = new ArrayList<>();

        for (Product x: prods) {

            if(category.equalsIgnoreCase(x.getCategory().name())) {

            }
            String image = "data:image/png;base64," + Base64.getEncoder().encodeToString(
                x.getImages().get(0).getImage_source()
            );
                
            String price = x.getPrice().toString();
            Integer product_id = x.getProduct_id();
            String status = x.getProduct_status().getDisplayName();
            String c_category = x.getCategory().getDisplayName();
            String name = x.getProduct_name();
            
            dtos.add(new ProductDTO(product_id, image, name, c_category, price, status));
        }

        return dtos;
    }
}
