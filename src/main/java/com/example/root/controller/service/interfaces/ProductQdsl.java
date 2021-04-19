package com.example.root.controller.service.interfaces;

import com.example.root.dao.entity.ProductEntity;

import java.util.List;

public interface ProductQdsl {
    List<ProductEntity> findRecently();
}
