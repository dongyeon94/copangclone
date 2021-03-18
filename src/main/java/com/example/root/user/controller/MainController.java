package com.example.root.user.controller;

import com.example.root.dao.UserEntity;
import com.example.root.user.service.impliment.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main(){
        return "index";
    }

}
