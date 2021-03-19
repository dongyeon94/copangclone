package com.example.root.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.PrintConversionEvent;

@Data
@Entity
@Getter
@Setter
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long        id;
    private String      name;
    private String      category;
    private Long        price;
    private Long        quantity;

}
