package com.tutorial.apidemo.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "ToDoList")
public class ToDoList {
    @Id
    private String id;
    private String name;
    private boolean completed;

    @OneToMany(mappedBy = "toDoList")
    @JsonIgnore
    private List<ToDoRegistration> registrations;

    public ToDoList(){}

    public ToDoList(String id, String name, boolean complete) {
        this.id = id;
        this.name = name;
        this.completed = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<ToDoRegistration> getRegistrations() {
        return registrations;
    }
}
