package com.example.root.user.service.impliment;

import com.example.root.dao.repo.UserDataRepo;
import com.example.root.dao.entity.UserEntity;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.user.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDataRepo userDataRepo;

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
    @Transactional
    public int update(UserEntity user) {
        UserEntity findUser = userDataRepo.findByEmail(user.getEmail());
        if (findUser != null && findUser.getPassword().equals(user.getPassword())){
            findUser.setPassword(user.getPassword());
            findUser.setNickName(user.getNickName());
            findUser.setAddress(user.getAddress());
            findUser.setPhoneNumber(user.getPhoneNumber());
            return ErrorsCodeDefine.SUSSESS;
        }
        else {
            return ErrorsCodeDefine.UNAUTHORIZED;
        }
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
