package com.tutorial.apidemo.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ToDoRegistration")
public class ToDoRegistration {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDoList toDoList;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserLogin userLogin;

    public ToDoRegistration(){}

    public ToDoRegistration(Long id, ToDoList toDoList, UserLogin userLogin) {
        this.id = id;
        this.toDoList = toDoList;
        this.userLogin = userLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
