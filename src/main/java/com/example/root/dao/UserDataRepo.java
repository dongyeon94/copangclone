package com.example.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String Email);
}
