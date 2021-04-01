package com.example.root.service;


import com.example.root.MockMvcTest;
import com.example.root.controller.service.impliment.UserServiceimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class UserServiceTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserServiceimpl userServiceimpl;

    @Test
    void createTest() throws Exception{
        mockMvc.perform(post("/user/signup")
                            .param("email","test@a.com")
                            .param("password","1234"))
                .andExpect(status().is3xxRedirection());
    }
}
