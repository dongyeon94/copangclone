package com.example.root.controller.service.impliment;

import com.example.root.controller.service.interfaces.ProductQdsl;
import com.example.root.dao.entity.ProductEntity;
import com.example.root.dao.entity.QProductEntity;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductDsqlIml extends QuerydslRepositorySupport {

    public ProductDsqlIml() {
        super(ProductEntity.class);
    }

    public List<ProductEntity> categoryIndex(String category){
        QProductEntity qProductEntity = QProductEntity.productEntity;
        JPQLQuery query = from(qProductEntity)
                                .where(qProductEntity.category.eq(category))
                                .orderBy(qProductEntity.id.desc());
        return query.fetch();
    }

    public List<ProductEntity> findRecently() {
        QProductEntity qProductEntity = QProductEntity.productEntity;
        JPQLQuery query = from(qProductEntity)
                                .where(qProductEntity.id.eq(41L))
                                .orderBy(qProductEntity.id.desc());
        List<ProductEntity> lists = query.fetch();
        System.out.println(lists.get(0));
        return null;
    }


}
