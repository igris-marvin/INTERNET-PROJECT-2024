package com.sanienterprise.dawn.api.service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.persistence.entity.Image;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.entity.Product.Category;
import com.sanienterprise.dawn.persistence.entity.Product.ProductStatus;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class AdminService {
    
    @Autowired
    private ProductRepository proRepo;

    public void createProduct(Product product, String status, String category, List<MultipartFile> images_parts) {
        List<Image> images = new ArrayList<>();

        String image_name = "";
        byte[] image_source = null;

        try {
            for(MultipartFile x: images_parts) {
                image_name = x.getOriginalFilename();
                image_source = x.getBytes();

                Image image = new Image(image_name, image_source);

                images.add(image);
            }

            product.setProduct_status(getSelectedStatus(status));
            product.setCategory(getSelectedCategory(category));
            product.setDate_added(Date.from(Instant.now()));
            product.setImages(images);

            System.out.println(product.toString());

            proRepo.save(product);

        } catch(IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private Category getSelectedCategory(String category) {
        Category[] categoryArray = Product.Category.values();

        for(int x = 0; x < categoryArray.length; x++) {

            if(categoryArray[x].name().equalsIgnoreCase(category)) {
                return categoryArray[x];
            }
        }

        return null;
    }

    private ProductStatus getSelectedStatus(String status) {
        ProductStatus[] statusArray = Product.ProductStatus.values();

        for(int x = 0; x < statusArray.length; x++) {

            if(statusArray[x].name().equalsIgnoreCase(status)) {
                return statusArray[x];
            }
        }

        return null;
    }

    public List<String> getStatuses() {
        ProductStatus[] statusArray = Product.ProductStatus.values();

        List<String> statuses = new ArrayList<>();

        for(int x = 0; x < statusArray.length; x++) {
            statuses.add(statusArray[x].name());
        }

        return statuses;
    }

    public List<String> getCategories() {
        Category[] categoryArray = Product.Category.values();

        List<String> categories = new ArrayList<>();

        for(int x = 0; x < categoryArray.length; x++) {
            categories.add(categoryArray[x].name());
        }

        return categories;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = proRepo.findAll();

        List<ProductDTO> product_dtos = new ArrayList<>();

        for(Product x: products) {

            ProductDTO dto = new ProductDTO();

            dto.setProduct_id(x.getProduct_id());
            dto.setImage("data:image/png;base64," +
                Base64
                    .getEncoder()
                    .encodeToString(
                        x
                            .getImages()
                            .get(0)
                            .getImage_source()
                    )
                );
            dto.setName(x.getProduct_name());
            dto.setCategory(getDTOCategory(x.getCategory().name()));
            dto.setPrice("R" + x.getPrice());

            product_dtos.add(dto);
        }

        return product_dtos;
    }

    private String getDTOCategory(String name) {
        String category = "";

        List<String> categories = new ArrayList<>();

        return category;
    }
}
