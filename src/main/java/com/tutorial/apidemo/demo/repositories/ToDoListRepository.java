package com.tutorial.apidemo.demo.repositories;

import com.tutorial.apidemo.demo.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, String>, PagingAndSortingRepository<ToDoList, String> {
    List<ToDoList> findByName(String name);
}
