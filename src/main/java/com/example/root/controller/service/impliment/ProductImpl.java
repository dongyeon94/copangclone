package com.example.root.controller.service.impliment;

import com.example.root.dao.entity.ProductEntity;
import com.example.root.dao.repo.ProductRepo;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.controller.service.interfaces.ProductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductorService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public int createProduct(ProductEntity productEntity) {
        productRepo.save(productEntity);
        return ErrorsCodeDefine.SUSSESS;
    }

    @Override
    public List<ProductEntity>readAllProduct() {
        List<ProductEntity> productList = productRepo.findAll();
        return productList;
    }
}
