package com.example.root.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id@GeneratedValue
    private Long        id;

    private String      email;
    private String      password;
    private String      nickName;
    private String      address;
    private String      phoneNumber;
    private String      authority;

}
