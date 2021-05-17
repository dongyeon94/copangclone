package com.example.root.controller.service.interfaces;

import com.example.root.dao.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public int create(UserEntity user);
    public UserEntity read(String email);
    public int update(UserEntity user);
    public int delete(UserEntity user);
    public int passwordInitialization(String email);

//    public int login(UserEntity user);
}
