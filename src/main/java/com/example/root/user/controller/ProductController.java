package com.example.root.user.controller;

import com.example.root.dao.ProductEntity;
import com.example.root.user.service.impliment.ProductImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private String productPage = "product/";

    @Autowired
    private ProductImpl product;

    @GetMapping("/read")
    public String searchProduct() {
        return productPage + "read";
    }

    @GetMapping("/create")
    public String createProduct(){
        return productPage + "create";
    }

    @PostMapping("/create")
    public String createProductPost(ProductEntity productEntity){
        if (product.createProduct(productEntity) == 200) {
            return "redirect:/product/read";
        }
        return "redirect:/product/create";
    }


}
