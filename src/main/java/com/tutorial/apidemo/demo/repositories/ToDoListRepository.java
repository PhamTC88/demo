package com.tutorial.apidemo.demo.repositories;

import com.tutorial.apidemo.demo.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, String> {
    List<ToDoList> findByName(String name);
}
