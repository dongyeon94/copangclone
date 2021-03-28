package com.example.root.user.controller;


import com.example.root.dao.entity.UserEntity;
import com.example.root.user.service.impliment.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private String userPage = "user/";

    @Autowired
    private UserServiceimpl userServiceimpl;

    @GetMapping("/mypage")
    public String myPage(Model model, UserEntity user){
        UserEntity users = userServiceimpl.read(user);
        model.addAttribute("userData", users);
        return userPage + "mypage";
    }



    @GetMapping("/login")
    public String loginPage(){
        return userPage + "login";
    }

//    @PostMapping("/login")
//    public String loginEvent(UserEntity user, Model model){
//        int result = userServiceimpl.login(user);
//        if (result == 200) {
//            model.addAttribute("errorcode",result);
//            UserEntity users = userServiceimpl.read(user);
//            model.addAttribute("userData", users);
//            return userPage + "mypage";
//        }
//        else{
//            model.addAttribute("errorcode",result);
//            return userPage + "login";
//        }
//    }

    @GetMapping("/signup")
    public String signUpUser(){
        return userPage + "signup";
    }

    @PostMapping("/signup")
    public String signUpEvent(UserEntity user) {
        if (userServiceimpl.create(user) == 200) {
            return "redirect:/";
        }

        else{
            return "redirect:/user/signup";
        }
    }

    @GetMapping("/update")
    public String updateUser (UserEntity userEntity, Model model) {
        UserEntity users = userServiceimpl.read(userEntity);
        model.addAttribute("userData", users);
        return userPage + "mypageUpdate";
    }

    @PostMapping("/update")
    public String updateUserPost (UserEntity userEntity, Model model) {
        if (userServiceimpl.update(userEntity) == 200) {
            UserEntity users = userServiceimpl.read(userEntity);
            model.addAttribute("userData", users);
            return userPage + "mypage";
        }
        else {
            UserEntity users = userServiceimpl.read(userEntity);
            model.addAttribute("userData", users);
            return userPage + "mypageUpdate";
        }
    }

}
