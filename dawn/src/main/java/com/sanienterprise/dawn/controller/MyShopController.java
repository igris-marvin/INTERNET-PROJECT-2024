package com.sanienterprise.dawn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanienterprise.dawn.api.dto.ProductDTO;
import com.sanienterprise.dawn.api.service.ShopService;

@Controller
@RequestMapping("/shop")
public class MyShopController {

    @Autowired
    private ShopService shopServ;

    @GetMapping("/category")
    public String getCategory(
        Model model
    ) {
        //CART PRODUCTS

        return "shop";
    }

    @GetMapping("/browse")
    public String getBrowse(
        @RequestParam("category") String category,
        Model model
    ) {
        List<ProductDTO> prods = shopServ.getProductsByCategory(category);

        model.addAttribute("prods", prods);

        return "browse";
    }

    @GetMapping("/product")
    public String getProduct(
        @RequestParam("id") Integer id
    ) {
        return "product";
    }
}
