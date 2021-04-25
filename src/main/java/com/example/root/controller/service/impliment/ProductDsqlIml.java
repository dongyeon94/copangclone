package com.example.root.controller.service.impliment;

import com.example.root.dao.entity.ProductEntity;
import com.example.root.dao.entity.QProductEntity;
import com.example.root.dao.repo.ProductRepo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductRepo productRepo;

    public List<ProductEntity> searchIteam(String item) {
        QProductEntity qProductEntity = QProductEntity.productEntity;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProductEntity.category.like("%"+item+"%"));
        List<ProductEntity> list = (List<ProductEntity>) productRepo.findAll(builder);
        return list;
    }

    public List<ProductEntity> categoryIndex(String category) {
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
        return query.fetch();
    }


}
