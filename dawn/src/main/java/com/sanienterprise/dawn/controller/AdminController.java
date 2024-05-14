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

import com.sanienterprise.dawn.api.dto.CategoryCountDTO;
import com.sanienterprise.dawn.api.dto.CustomerDashDTO;
import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.api.service.AdminService;
import com.sanienterprise.dawn.persistence.entity.Product;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService admServ;
    
    @GetMapping("/dashboard")
    public String getAdmin(
        Model model
    ) {
        int prods_count = admServ.getNumberOfProducts();
        int users_count = admServ.getNumberOfUsers();

        CategoryCountDTO category_count_list = admServ.getCategoryCountListObject();

        model.addAttribute("prods_count", prods_count);
        model.addAttribute("users_count", users_count);
        model.addAttribute("category_count_list", category_count_list);

        return "dashboard";
    }

    //create product
    @GetMapping("/create")
    public String getCreate(
        Model model
    ) {
        Product product = new Product();
        List<String> statuses = admServ.getProductStatuses();
        List<String> categories = admServ.getProductCategories();

        model.addAttribute("product", product);
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);

        return "create_product_dash";
    }

    @ModelAttribute(name = "success")
    public boolean getSuccess() {
        return false;
    }

    @PostMapping("/create")
    public String posCreate(
        Model model
    ) {
        Product product = (Product) model.getAttribute("product");

        if(product != null) {
            System.out.println("Product: " + product.toString());
        } else {
            System.out.println("Product not created!");
        }

        return "";
    }

    //products
    @GetMapping("/products")
    public String getProducts(
        Model model
    ) {
        List<ProductDTO> products = admServ.getAllProducts();

        model.addAttribute("products", products);

        return "product_dash";
    }

    //delete
    @GetMapping("/delete")
    public String getDelete(
        @RequestParam("id") Integer id
    ) {
        admServ.removeProductById(id);

        return "redirect:/admin/products";
    }

    //modify
    @GetMapping("/modify")
    public String getModify(
        Model model,
        @RequestParam("id") Integer id
    ) {
        //get all product info

        return "modify_product_dash";
    }

    //users
    @GetMapping("/users")
    public String getUsers(
        Model model
    ) {
        List<CustomerDashDTO> customers = admServ.getAllCustomers();

        model.addAttribute("customers", customers);

        return "user_dash";
    }

    //search
    @GetMapping("/search")
    public String getSearch() {
        return "search_dash";
    }

    @PostMapping("/search") 
    public String posSearch(
        Model model, 
        @RequestParam("search_id") Integer id
    ) {
        ProductDTO product = admServ.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "search_dash";
    }

    //category

    //profile

    //register
}
