package com.example.root.controller.controller;

import com.example.root.controller.service.impliment.ProductDsqlIml;
import com.example.root.dao.entity.ProductEntity;
import com.example.root.controller.service.impliment.ProductImpl;
import com.example.root.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private String productPage = "product/";

    @Autowired
    private ProductImpl product;

    @Autowired
    private ProductDsqlIml productDsqlIml;



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

    @GetMapping("/search/")
    public String searchPage(String item){
        System.out.println(item);
        System.out.println(productDsqlIml.searchIteam(item));

        return productPage + "search";
    }

    @GetMapping("/users/buy")
    @ResponseBody
    public List readMyBuyProduct(){
        return product.readAllProduct();
    }

    @GetMapping("/test")
    public String testMethod(){
        productDsqlIml.findRecently();
        return "redirect:/product/read";
    }
}
