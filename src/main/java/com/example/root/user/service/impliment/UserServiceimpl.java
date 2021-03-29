package com.example.root.user.service.impliment;

import com.example.root.dao.repo.UserDataRepo;
import com.example.root.dao.entity.UserEntity;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceimpl implements UserService , UserDetailsService {

    @Autowired
    private UserDataRepo userDataRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public int create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority("ROLE_USER");
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity users =  userDataRepo.findByEmail(email);
        if (users == null) {
            log.info("user null");
            throw new UsernameNotFoundException(email);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(users.getAuthority()));
        return new User(users.getEmail(), users.getPassword(), authorities);
    }
}
