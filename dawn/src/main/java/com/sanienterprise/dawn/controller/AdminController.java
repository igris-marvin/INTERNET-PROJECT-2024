package com.sanienterprise.dawn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.api.service.AdminService;
import com.sanienterprise.dawn.persistence.entity.Product;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService admServ;
    
    @GetMapping
    public String getAdmin() {
        return "admin_menu";
    }

    //CREATE
    @GetMapping("/create")
    public String getCreate(
        Model model
    ) {
        Product product = new Product();

        List<String> statuses = admServ.getStatuses();
        List<String> categories = admServ.getCategories();

        model.addAttribute("product", product);
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);

        return "admin_create";
    }

    @PostMapping("/create")
    public String posCreate(
        @ModelAttribute(name = "product") Product product,
        @RequestParam("product_status") String status,
        @RequestParam("category") String category,
        @RequestParam("image_parts") List<MultipartFile> image_parts
    ) {
        admServ.createProduct(product, status, category, image_parts);

        // System.out.println(product.toString() + " - " + status + " - " + category + " - " + image_parts.size());

        return "redirect:/admin";
    }

    //SEARCH
    @GetMapping("/search")
    public String getSearch(
        Model model
    ) {
        List<ProductDTO> products = admServ.getAllProducts();

        if(products == null) {
            model.addAttribute("message", "No products available");
        } else {
            model.addAttribute("products", products);
        }

        return "admin_search";
    }

    //MODIFY

    //REMOVE

    //REGISTER
}
