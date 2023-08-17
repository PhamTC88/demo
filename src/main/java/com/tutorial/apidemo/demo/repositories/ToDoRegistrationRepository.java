package com.tutorial.apidemo.demo.repositories;

import com.tutorial.apidemo.demo.models.ToDoRegistration;
import com.tutorial.apidemo.demo.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRegistrationRepository extends JpaRepository<ToDoRegistration, Long> {
    List<ToDoRegistration> findByUserLogin(UserLogin userLogin);
}
