package com.example.root.user.service.interfaces;

import com.example.root.dao.entity.ProductEntity;

import java.util.List;


public interface ProductorService {
    public int createProduct(ProductEntity productEntity);
    public List<ProductEntity> readAllProduct();
}
