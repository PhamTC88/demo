package com.tutorial.apidemo.demo.repositories;

import com.tutorial.apidemo.demo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>, PagingAndSortingRepository<Project, Long> {
    List<Project> findByName(String name);
}
