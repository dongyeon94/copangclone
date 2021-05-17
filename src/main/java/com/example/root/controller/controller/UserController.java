package com.example.root.controller.controller;

import com.example.root.configs.CustomUser;
import com.example.root.dao.entity.UserEntity;
import com.example.root.controller.service.impliment.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private String userPage = "user/";

    @Autowired
    private UserServiceimpl userServiceimpl;


    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal CustomUser userEntity, Model model){
        model.addAttribute("userData",userServiceimpl.read(userEntity.getEmail()));
        return userPage + "mypage";
    }

    @PostMapping("/mypage/myInfo")
    @ResponseBody
    public UserEntity myInfo(@AuthenticationPrincipal CustomUser userEntity){
        return userServiceimpl.read(userEntity.getEmail());
    }

    @GetMapping("/mypage/buketList")
    public String buketList(){
        return userPage + "buketlist";
    }


    @GetMapping("/login")
    public String loginPage(){
        return userPage + "login";
    }

    @GetMapping("/signup")
    public String signUpUser(){
        return userPage + "signup";
    }

    @PostMapping("/signup")
    public String signUpEvent(UserEntity user) {
        if (userServiceimpl.create(user) == 200) {
            return "index";
        }
        else{
            return "redirect:/user/signup";
        }
    }

    @GetMapping("/user/{username}/{token}")
    public String accessUserEmailToken(@PathVariable("token") String token, @PathVariable("username") String username){
        System.out.println(token);
        System.out.println(username);
        return "return/accessEmailToken";
    }

    @GetMapping("/update")
    public String updateUser (@AuthenticationPrincipal CustomUser userEntity, Model model) {
        UserEntity users = userServiceimpl.read(userEntity.getEmail());
        model.addAttribute("userData", users);
        return userPage + "mypageUpdate";
    }

    @PostMapping("/update")
    public String updateUserPost (UserEntity userEntity, Model model) {
        if (userServiceimpl.update(userEntity) == 200) {
            UserEntity users = userServiceimpl.read(userEntity.getEmail());
            model.addAttribute("userData", users);
            return userPage + "mypage";
        }
        else {
            UserEntity users = userServiceimpl.read(userEntity.getEmail());
            model.addAttribute("userData", users);
            return userPage + "mypageUpdate";
        }
    }

    @GetMapping("/findPassword")
    public String findUserPasswordPage (String email) {
        return userPage + "findPassword";
    }

    @PostMapping("/findPassword")
    public String findUserPassword (String email) {
        userServiceimpl.passwordInitialization(email);
        return "redirect:/user/login";
    }
}
