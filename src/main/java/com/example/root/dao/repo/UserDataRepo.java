package com.example.root.dao.repo;

import com.example.root.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);
}
