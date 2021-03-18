package com.example.root.user.controller;


import com.example.root.dao.UserEntity;
import com.example.root.user.service.impliment.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserServiceimpl userServiceimpl;

    @GetMapping("/mypage")
    public String myPage(Model model, UserEntity user){
//        UserEntity users = userServiceimpl.read(user);

        UserEntity us = new UserEntity();
        us.setEmail("dongyeon94@naver.com");
        UserEntity users = userServiceimpl.read(us);
        model.addAttribute("userData", users);
        return "mypage";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String loginEvent(UserEntity user, Model model){
        int result = userServiceimpl.login(user);
        if (result == 200) {
            model.addAttribute("errorcode",result);
            return "redirect:/mypage";
        }
        else{
            model.addAttribute("errorcode",result);
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signUpUser(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpEvent(UserEntity user) {
        if (userServiceimpl.create(user) == 200) {
            return "redirect:/";
        }
        else{
            return "redirect:/signup";
        }
    }

}
