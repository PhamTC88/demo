package com.tutorial.apidemo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnListProjects() throws Exception {
        this.mockMvc.perform(get("/api/v2/projects")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnProject() throws Exception {
        this.mockMvc.perform(get("/api/v2/projects/99")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Heathcote - Pacocha")));
    }
}
