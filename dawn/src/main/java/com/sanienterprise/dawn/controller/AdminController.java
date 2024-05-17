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
import com.sanienterprise.dawn.api.dto.CreateProductDTO;
import com.sanienterprise.dawn.api.dto.CustomerDashDTO;
import com.sanienterprise.dawn.api.dto.ModifyImageDTO;
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
        ModifyProductDTO modify = admServ.getModifiableProductObject(id);

        List<String> statuses = admServ.getProductStatuses();
        List<String> categories = admServ.getProductCategories();
        String flag = "false";

        System.out.println(modify.getImages().size());
        
        model.addAttribute("modify", modify);
        model.addAttribute("statuses", statuses);
        model.addAttribute("categories", categories);
        model.addAttribute("success", flag);

        return "modify_product_dash";
    }

    @PostMapping("/modify")
    public String posModify(
        @RequestParam("p_id") Integer p_id,
        @RequestParam("p_name") String p_name,
        @RequestParam("p_style") String p_style,
        @RequestParam("p_length") Double p_length,
        @RequestParam("p_width") Double p_width,
        @RequestParam("p_height") Double p_height,
        @RequestParam("p_price") Double p_price,
        @RequestParam("p_quantity") Integer p_quantity,
        @RequestParam("p_category") String p_category,
        @RequestParam("p_status") String p_status,
        @RequestParam("p_description") String p_description,
        @RequestParam("image_files") List<MultipartFile> image_files,
        Model model
    ) {
        //UPDATE PRODUCT
        String flag = "true";

        boolean validate = admServ.updateProduct(p_id, p_name, p_style, p_length, p_width, p_height, p_price, p_quantity, p_category, p_status, p_description, image_files);

        model.addAttribute("success", flag);

        return "redirect:/admin/modify?id=" + p_id;
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
