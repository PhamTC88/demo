package com.tutorial.apidemo.demo.controllers;

import com.tutorial.apidemo.demo.models.ResponseObject;
import com.tutorial.apidemo.demo.models.ToDoList;
import com.tutorial.apidemo.demo.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/ToDoList")
public class ToDoListController {
    //DI = Dependency Injection
    @Autowired
    private ToDoListRepository toDoListRepository;

    @GetMapping("")
    //this request is : http://localhost:8080/api/v1/ToDoList
    List<ToDoList> getAllToDoList(){
        return toDoListRepository.findAll();
    }

    //Get detail ToDoList
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable String id){
        Optional<ToDoList> foundToDoList = toDoListRepository.findById(id);
        return foundToDoList.isPresent() ?
                ResponseEntity.ok().body(
                        new ResponseObject("ok", "Query ToDoList successfully", foundToDoList)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find ToDoList with id = " +id, "")
                );
    }

    //insert new ToDoList with POST method
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertToDoList(@RequestBody ToDoList toDoList){
        //2 ToDoList must not have the same name !
        List<ToDoList> foundToDoList = toDoListRepository.findByName(toDoList.getName().trim());
        if(foundToDoList.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "ToDo name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert ToDoList successfully", toDoListRepository.save(toDoList))
        );
    }

    //update
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateToDoList(@RequestBody ToDoList toDoList, @PathVariable String id){
        ToDoList updateToDoList = toDoListRepository.save(toDoList);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update ToDoList successfully", updateToDoList)
        );
    }

    //delete
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteToDoList(@PathVariable String id){
        boolean exists = toDoListRepository.existsById(id);
        if(exists){
            toDoListRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete ToDoList successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find ToDoList to delete", "")
        );
    }
}
