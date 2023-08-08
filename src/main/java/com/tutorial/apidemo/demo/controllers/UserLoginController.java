package com.tutorial.apidemo.demo.controllers;

import com.tutorial.apidemo.demo.models.ResponseObject;
import com.tutorial.apidemo.demo.models.UserLogin;
import com.tutorial.apidemo.demo.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            if(userLogin.getPassword() == null || userLogin.getPassword().isBlank() || !foundUserLogin.get(0).getPassword().equals(userLogin.getPassword().trim())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "wrong password", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "get token by login", foundUserLogin.get(0).getToken())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "wrong username", "")
            );
        }

    }
}
