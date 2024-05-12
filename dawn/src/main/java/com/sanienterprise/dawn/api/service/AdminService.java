package com.sanienterprise.dawn.api.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanienterprise.dawn.api.dto.CategoryCountDTO;
import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.persistence.entity.Product;
import com.sanienterprise.dawn.persistence.entity.Product.Category;
import com.sanienterprise.dawn.persistence.repository.PatronRepository;
import com.sanienterprise.dawn.persistence.repository.ProductRepository;

@Service
public class AdminService {
    
    @Autowired
    private ProductRepository proRepo;

    @Autowired
    private PatronRepository patRepo;

    public int getNumberOfProducts() {
        int count = Integer.parseInt(Long.toString(proRepo.count()));

        return count;
    }

    public int getNumberOfUsers() {
        
        int count = patRepo.countPatronByRole("CUSTOMER");

        return count;
    }

    public CategoryCountDTO getCategoryCountListObject() {
        CategoryCountDTO count_dto = new CategoryCountDTO();

        List<Product> prod_list = proRepo.findAll();

        count_dto.setBar_chair_count(getCategoryCount(Product.Category.BAR_CHAIR, prod_list));
        count_dto.setBathroom_caddy_count(getCategoryCount(Product.Category.BATHROOM_CADDY, prod_list));
        count_dto.setCarpet_count(getCategoryCount(Product.Category.CARPET, prod_list));
        count_dto.setChest_of_drawer_count(getCategoryCount(Product.Category.CHEST_OF_DRAWER, prod_list));
        count_dto.setCoffee_table_count(getCategoryCount(Product.Category.COFFEE_TABLE, prod_list));
        count_dto.setConsole_table_count(getCategoryCount(Product.Category.CONSOLE_TABLE, prod_list));
        count_dto.setDining_set_count(getCategoryCount(Product.Category.DINING_SET, prod_list));
        count_dto.setDressing_table_count(getCategoryCount(Product.Category.DRESSING_TABLE, prod_list));
        count_dto.setHeadboard_count(getCategoryCount(Product.Category.HEADBOARD, prod_list));
        count_dto.setLove_seat_count(getCategoryCount(Product.Category.LOVE_SEAT, prod_list));
        count_dto.setOccasional_chair_count(getCategoryCount(Product.Category.OCCASIONAL_CHAIR, prod_list));
        count_dto.setPedestal_count(getCategoryCount(Product.Category.PEDESTAL, prod_list));
        count_dto.setSleeper_couch_count(getCategoryCount(Product.Category.SLEEPER_COUCH, prod_list));
        count_dto.setStudy_table_count(getCategoryCount(Product.Category.STUDY_TABLE, prod_list));
        count_dto.setTv_stand_count(getCategoryCount(Product.Category.TV_STAND, prod_list));
        count_dto.setUsed_fridge_count(getCategoryCount(Product.Category.USED_FRIDGE, prod_list));

        return count_dto;
    }

    private int getCategoryCount(Category categoryType, List<Product> prod_list) {
        int count = 0;

        for(Product x: prod_list) {

            if(categoryType.name().equalsIgnoreCase(x.getCategory().name())) {
                count++;
            }
        }

        return count;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();

        List<Product> prods = proRepo.findAll();

        for(Product x: prods) {
            products.add(genProductInfo(x));
        }

        return products;
    }

    private ProductDTO genProductInfo(Product x) {
        Integer id = x.getProduct_id();
        String product_image = "data:image/png;base64," 
                + Base64
                    .getEncoder()
                    .encodeToString(
                        x
                            .getImages()
                            .get(0)
                            .getImage_source()
                    );
        String product_title = x.getProduct_name();
        String product_category = x.getCategory().getDisplayName();
        String product_price = Double.toString(x.getPrice());
        String product_quantity = Integer.toString(x.getQuantity());
        String product_added_date = x.getDate_added().toString();

        return new ProductDTO(id, product_image, product_title, product_category, product_price, product_quantity, product_added_date);
    }

    public void removeProductById(Integer id) {
        proRepo.deleteById(id);
    }
}
