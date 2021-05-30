package com.example.root.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.patterns.IToken;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id@GeneratedValue
    private Long        id;
    @Column(unique=true)
    private String      email;
    private String      password;
    private String      nickName;
    private String      address;
    private String      phoneNumber;
    private String      authority;
    private boolean     activate;
    private String      token;

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
    }

}
