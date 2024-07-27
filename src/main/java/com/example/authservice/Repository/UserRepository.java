package com.example.authservice.Repository;

import com.example.authservice.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo save(UserInfo userInfo);
    UserInfo findByUsername(String username);
}
