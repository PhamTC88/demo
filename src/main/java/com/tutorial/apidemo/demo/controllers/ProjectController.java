package com.tutorial.apidemo.demo.controllers;

import com.tutorial.apidemo.demo.models.Project;
import com.tutorial.apidemo.demo.models.ResponseObject;
import com.tutorial.apidemo.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v2/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    // http://localhost:8080/api/v2/projects
    @CrossOrigin
    @GetMapping("")
    List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // http://localhost:8080/api/v2/projects/pagination?_page=1&_limit=6&_sort=name
    @CrossOrigin
    @GetMapping("/pagination")
    List<Project> getAllProjectsWithPagination(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit, @RequestParam(name = "_sort", required = false) String sort){
        Pageable pageWithLimitElement;
        if(!sort.isEmpty() && sort != null) {
            pageWithLimitElement = PageRequest.of(page - 1, limit, Sort.by(sort));
        } else {
            pageWithLimitElement = PageRequest.of(page - 1, limit);
        }

        return projectRepository.findAll(pageWithLimitElement).getContent();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Project> foundProject = projectRepository.findById(id);
        return foundProject.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Get project successfully",foundProject)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find project with id = " + id, "")
                );
    }

    @CrossOrigin
    @PostMapping("")
    ResponseEntity<ResponseObject> insertProject(@RequestBody Project project){
        //2 project must not have the same name !
        List<Project> foundProject = projectRepository.findByName(project.getName());
        if(foundProject.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Project name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create new project successfully", projectRepository.save(project))
        );
    }

    @CrossOrigin
    @PutMapping("/{id}")
    Project updateProject(@RequestBody Project project, @PathVariable Long id){
        return projectRepository.save(project);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProject(@PathVariable Long id){
        boolean exists = projectRepository.existsById(id);
        if(!exists){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Project not found.", "")
            );
        }
        projectRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Project deleted successfully.", "")
        );
    }
}
