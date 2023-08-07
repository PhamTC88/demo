package com.tutorial.apidemo.demo.repositories;

import com.tutorial.apidemo.demo.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
    List<UserLogin> findByUsername(String username);
}
