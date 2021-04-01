package com.example.root.controller.controller;


import com.example.root.configs.CustomUser;
import com.example.root.configs.UserAccount;
import com.example.root.dao.entity.UserEntity;
import com.example.root.controller.service.impliment.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private String userPage = "user/";

    @Autowired
    private UserServiceimpl userServiceimpl;


    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal CustomUser userEntity, Model model){
        System.out.println(userEntity.getEmail());
        model.addAttribute("userData",userServiceimpl.read(userEntity.getEmail()));
        return userPage + "mypage";
    }

//    @GetMapping("/mypage")
//    public String myPage(Principal principal){
//
//        System.out.println(principal);
//
//        System.out.println(principal.getName());
//        System.out.println(principal.getClass());
//        System.out.println(principal.toString());
//
//        try{
//            CustomUser customUser = (CustomUser) principal;
//            System.out.println(customUser.getEmail());
//        }
//        catch (Exception e){
//
//        }
//
//        return userPage + "mypage";
//    }

//    @GetMapping("/mypage")
//    public String myPage(Authentication authentication){
//        System.out.println("-------");
//        try{
//            System.out.println(authentication);
//            WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            System.out.println("suc");
//            System.out.println(web);
//            System.out.println(userDetails);
//            System.out.println(web.getSessionId());
//            System.out.println(web.getRemoteAddress());
//            System.out.println(userDetails.getUsername());
//            System.out.println(userDetails.getAuthorities());
//            System.out.println(userDetails.getClass());
//
//
//
//        }catch (Exception e){
//            System.out.println("fail");
//            System.out.println(e.toString());
//        }
//        System.out.println("-------");
//        return userPage + "mypage";
//    }



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

}
