package com.sanienterprise.dawn.controller;

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

import com.sanienterprise.dawn.api.dto.CategoryCountDTO;
import com.sanienterprise.dawn.api.dto.CreateModifyProductDTO;
import com.sanienterprise.dawn.api.dto.CreateProductDTO;
import com.sanienterprise.dawn.api.dto.CustomerDashDTO;
import com.sanienterprise.dawn.api.dto.ModifyProductDTO;
import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.api.service.AdminService;

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
        CreateProductDTO product = new CreateProductDTO();
        List<String> statuses = admServ.getProductStatuses();
        List<String> categories = admServ.getProductCategories();
        String flag = "false";

        model.addAttribute("product", product);
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("success", flag);

        return "create_product_dash";
    }

    @PostMapping("/create")
    public String posCreate(
        @RequestParam("image_files") List<MultipartFile> files,
        @ModelAttribute("product") CreateProductDTO product,
        @ModelAttribute("success") String flag,
        Model model
    ) {

        boolean bool_flag = admServ.createProduct(product, files);

        if(bool_flag) {
            flag = "true";
        } else {
            flag = "not_true";
        }

        model.addAttribute("success", flag);


        return "create_product_dash";
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
        @RequestParam("id") Integer id,
        Model model
    ) {
        CreateProductDTO product = new CreateProductDTO();
        ModifyProductDTO modify = admServ.getModifiableProductObject(id);

        CreateModifyProductDTO product_dto = new CreateModifyProductDTO(product, modify);

        List<String> statuses = admServ.getProductStatuses();
        List<String> categories = admServ.getProductCategories();
        String flag = "false";
        
        model.addAttribute("product_dto", product_dto);
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("success", flag);

        return "modify_product_dash";
    }

    @PostMapping("/modify")
    public String posModify(
        @ModelAttribute("product_dto") CreateModifyProductDTO product_dto,
        Model model
    ) {
        //UPDATE PRODUCT

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

    //profile

    //register
}
