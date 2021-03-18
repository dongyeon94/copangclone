package com.example.root.user.service.interfaces;

import com.example.root.dao.ProductEntity;
import org.springframework.stereotype.Service;


public interface ProductorService {
    public int createProduct(ProductEntity productEntity);
}
