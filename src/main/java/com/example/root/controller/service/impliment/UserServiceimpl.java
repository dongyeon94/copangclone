package com.example.root.controller.service.impliment;

import com.example.root.configs.CustomUser;
import com.example.root.configs.UserAccount;
import com.example.root.dao.repo.UserDataRepo;
import com.example.root.dao.entity.UserEntity;
import com.example.root.errorcode.ErrorsCodeDefine;
import com.example.root.controller.service.interfaces.UserService;
import com.mysql.cj.xdevapi.SessionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.util.Arrays;


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
    public UserEntity read(String eamil) {
        return userDataRepo.findByEmail(eamil);
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

//    @Override
//    public int login(UserEntity user) {
//
//        UserEntity findUser = userDataRepo.findByEmail(user.getEmail());
//        if (findUser != null) {
//            return ErrorsCodeDefine.SUSSESS;
//        }
//        return ErrorsCodeDefine.NOT_FOUND;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity =  userDataRepo.findByEmail(email);
        if (userEntity == null) {
            log.info("user null");
            throw new UsernameNotFoundException(email);
        }
        CustomUser customUser = new CustomUser(userEntity);
        customUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userEntity.getAuthority())));
        customUser.setEnabled(true);
        customUser.setAccountNonLocked(true);
        customUser.setAccountNonExpired(true);
        customUser.setCredentialsNonExpired(true);

        return customUser;

    }

    public void login(UserEntity userEntity){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                        new UserAccount(userEntity,userEntity.getEmail()),
                                        userEntity.getPassword(),
                                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
