package com.example.root.controller.controller;

import com.example.root.controller.service.impliment.ProductQdslIml;
import com.example.root.dao.entity.ProductEntity;
import com.example.root.controller.service.impliment.ProductImpl;
import com.example.root.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private String productPage = "product/";

    @Autowired
    private ProductImpl product;

    @Autowired
    private ProductQdslIml prods;

    @GetMapping("/read")
    public String searchProduct(Model model) {
        model.addAttribute("productList",product.readAllProduct());
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

    @PostMapping("/users/buy")
    @ResponseBody
    public List readMyBuyProduct(UserEntity userEntity){
        return product.readAllProduct();
    }

    @GetMapping("/test")
    public String testMethod(){
        prods.findRecently();
        return "redirect:/product/read";
    }
}
