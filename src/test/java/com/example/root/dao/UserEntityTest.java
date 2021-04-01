package com.example.root.dao;

import com.example.root.dao.entity.UserEntity;
import com.example.root.dao.repo.UserDataRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityTest {

    @Autowired
    UserDataRepo userDataRepo;
    @Autowired
    TestEntityManager testEntityManager;


    @Test
    @DisplayName("User Save Test")
    void Save() {
        UserEntity user = new UserEntity();
        user.setEmail("logout94@naver.com");
        user.setPassword("1234");
        user.setNickName("강남건물주");
        user.setAddress("강남구 역삼동");
        user.setPhoneNumber("010-8888-9999");
        testEntityManager.persist(user);

        assertEquals(user, testEntityManager.find(UserEntity.class, user.getId()));
    }

    @Test
    @DisplayName("User Save And Select Test")
    void SaveAndSelectTest() {
        UserEntity user = new UserEntity();
        user.setEmail("logout94@naver.com");
        user.setPassword("1234");
        user.setNickName("강남건물주");
        user.setAddress("강남구 역삼동");
        user.setPhoneNumber("010-8888-9999");
        testEntityManager.persist(user);

        UserEntity selectedUser = userDataRepo.findByEmail(user.getEmail());
        assertEquals(user, selectedUser);
    }


}
