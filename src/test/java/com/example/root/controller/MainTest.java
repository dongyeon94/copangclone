package com.example.root.controller;

import com.example.root.MockMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class MainTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void mainTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void mainPrincipalTest() throws Exception {
        mockMvc.perform(post("/user/login")
                .param("email","dongyeon94@naver.com")
                .param("password","1234"))
                .andExpect(status().is3xxRedirection());
    }
}
