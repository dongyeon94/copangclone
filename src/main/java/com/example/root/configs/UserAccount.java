package com.example.root.configs;


import com.example.root.dao.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Arrays;

@Getter
public class UserAccount extends User {

    private UserEntity userEntity;
    private String email;

    public UserAccount(UserEntity userEntity,String email){
        super(userEntity.getNickName(), userEntity.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        this.userEntity = userEntity;
        this.email = email;
    }


    public String getEmail(){
        return email;
    }
}
