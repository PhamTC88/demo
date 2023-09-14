package com.tutorial.apidemo.demo;

import com.tutorial.apidemo.demo.repositories.ProjectRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void testProjectRepository() {
        assertThat(projectRepository).isNotNull();

        assertThat(projectRepository.findAll()).hasSize(84);

        assertThat(projectRepository.findById(100L).get().getName()).isEqualTo("Johnson - Kutch test");
    }
}
