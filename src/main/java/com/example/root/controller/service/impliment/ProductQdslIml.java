package com.example.root.controller.service.impliment;

import com.example.root.controller.service.interfaces.ProductQdsl;
import com.example.root.dao.entity.ProductEntity;
import com.example.root.dao.entity.QProductEntity;
import com.mysql.cj.QueryResult;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductQdslIml extends QuerydslRepositorySupport implements ProductQdsl {

    public ProductQdslIml() {
        super(ProductEntity.class);
    }



    private JPAQueryFactory jpaQueryFactory;

    public List<ProductEntity> findRecently() {
//        QProductEntity productEntity = new QProductEntity();
        QProductEntity qProductEntity = QProductEntity.productEntity;
        JPQLQuery query = from(qProductEntity).orderBy(qProductEntity.id.desc());
        System.out.println(qProductEntity.name.eq("t1"));
        System.out.println("******************************************");
        System.out.println("******************************************");
        System.out.println("----------------------------");
        System.out.println(query.fetchResults().toString());
        System.out.println("----------------------------");
        System.out.println(query.fetchAll());
        System.out.println("----------------------------");
        System.out.println(query.fetch());
        System.out.println("----------------------------");
        System.out.println(query);
        System.out.println("----------------------------");
        System.out.println("******************************************");
        System.out.println("******************************************");

//
//        JPAQuery query = new JPAQuery(em);
//        QProductEntity qq = new QProductEntity("m");
//        List<ProductEntity> lili = query.from(qq).where().orderBy(qq.id.desc()).f;

        return null;
    }


}
