package com.example.root.user.service.impliment;

import com.example.root.dao.UserDataRepo;
import com.example.root.dao.UserEntity;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.user.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserDataRepo userDataRepo;

//    Todo password endcoding

    @Override
    public int create(UserEntity user) {
        userDataRepo.save(user);
        return ErrorsCodeDefine.SUSSESS;
    }

    @Override
    public UserEntity read(UserEntity user) {
        return userDataRepo.findByEmail(user.getEmail());
    }

    @Override
    public int update(UserEntity user) {
        return ErrorsCodeDefine.SUSSESS;
    }

    @Override
    public int delete(UserEntity user) {
        return ErrorsCodeDefine.SUSSESS;
    }

    @Override
    public int login(UserEntity user) {
        UserEntity findUser = userDataRepo.findByEmail(user.getEmail());
        if (findUser != null) {
            return ErrorsCodeDefine.SUSSESS;
        }
        return ErrorsCodeDefine.NOT_FOUND;
    }

}
