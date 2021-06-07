package com.example.root.controller.controller;

import com.example.root.configs.CurrentUser;
import com.example.root.dao.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String Main(Principal principal, Model model){
        if (principal != null){
            log.info("user login : "+ principal.getName());
        }
        return "index";
    }
}
