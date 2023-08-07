package com.tutorial.apidemo.demo.controllers;

import com.tutorial.apidemo.demo.models.ResponseObject;
import com.tutorial.apidemo.demo.models.UserLogin;
import com.tutorial.apidemo.demo.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ToDoList")
public class UserLoginController {
    @Autowired
    private UserLoginRepository userLoginRepository;

    @PostMapping("/login")
    ResponseEntity<ResponseObject> postToken(@RequestBody UserLogin userLogin){
        List<UserLogin> foundUserLogin = userLoginRepository.findByUsername(userLogin.getUsername());

        if(foundUserLogin.size() > 0) {
            System.out.println("login successfully, token: " + foundUserLogin.get(0).getToken());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "get token by login", foundUserLogin.get(0).getToken())
            );
        } else {
            System.out.println("login failed");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "can't find userlogin", "")
            );
        }

    }
}
