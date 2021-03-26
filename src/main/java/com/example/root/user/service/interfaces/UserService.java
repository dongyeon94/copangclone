package com.example.root.user.service.interfaces;

import com.example.root.dao.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public int create(UserEntity user);
    public UserEntity read(UserEntity user);
    public int update(UserEntity user);
    public int delete(UserEntity user);

    public int login(UserEntity user);
}
