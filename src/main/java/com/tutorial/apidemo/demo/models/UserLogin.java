package com.tutorial.apidemo.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "UserLogin")
public class UserLogin {
    @Id
    private String username;
    private String password;
    private String token;

    @OneToMany(mappedBy = "userLogin")
    @JsonIgnore
    private List<ToDoRegistration> registrations;

    public UserLogin(){}

    public UserLogin(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ToDoRegistration> getRegistrations() {
        return registrations;
    }
}
