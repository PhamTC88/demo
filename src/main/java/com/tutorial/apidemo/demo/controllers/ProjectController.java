package com.tutorial.apidemo.demo.controllers;

import com.tutorial.apidemo.demo.models.Project;
import com.tutorial.apidemo.demo.models.ResponseObject;
import com.tutorial.apidemo.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.system.JavaVersion;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    // http://localhost:8080/api/v2/projects
    @CrossOrigin
    @GetMapping("")
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // http://localhost:8080/api/v2/projects/pagination?_page=1&_limit=6&_sort=name
    @CrossOrigin
    @GetMapping("/pagination")
    public List<Project> getAllProjectsWithPagination(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit, @RequestParam(name = "_sort", required = false) String sort){
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
    public Project findById(@PathVariable Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find project with id: " + id));
    }

    @CrossOrigin
    @PostMapping("")
    public Project insertProject(@RequestBody Project project){
        return projectRepository.save(project);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Project updateProject(@RequestBody Project project, @PathVariable Long id){
        return projectRepository.save(project);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public Project deleteProject(@PathVariable Long id){
        Project deletedProject = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find project with id: " + id));
        projectRepository.deleteById(id);
        return deletedProject;
    }
}
