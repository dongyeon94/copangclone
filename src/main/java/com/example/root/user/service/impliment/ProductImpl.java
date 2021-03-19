package com.example.root.user.service.impliment;

import com.example.root.dao.entity.ProductEntity;
import com.example.root.dao.repo.ProductRepo;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.user.service.interfaces.ProductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductorService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public int createProduct(ProductEntity productEntity) {
        ProductEntity newPro = new ProductEntity();
        newPro.setName(productEntity.getName());
        newPro.setPrice(productEntity.getPrice());
        newPro.setQuantity(productEntity.getQuantity());
        newPro.setCategory(productEntity.getCategory());
        productRepo.save(newPro);
        return ErrorsCodeDefine.SUSSESS;
    }

    @Override
    public List<ProductEntity> readAllProduct() {
        List<ProductEntity> productList = productRepo.findAll();
        return productList;
    }
}
