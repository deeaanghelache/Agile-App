package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.controllers.ProjectController;
import com.unibuc.appbackend.dtos.ProjectRequest;
import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @Test
    public void createProject() throws Exception {
        UUID uuid = UUID.randomUUID();
        ProjectRequest projectRequest = new ProjectRequest("Project1", "Description");
        Project project = new Project(uuid, "Project1", "Description");

        when(projectService.create(any())).thenReturn(project);

        mockMvc.perform(post("/project/createProject")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(projectRequest.getDescription()))
                .andExpect(jsonPath("$.name").value(projectRequest.getName()));
    }

    @Test
    public void deleteProject() throws Exception {
        UUID projectId = UUID.randomUUID();

        mockMvc.perform(delete("/project/delete/{id}", projectId))
                .andExpect(status().isOk());

        verify(projectService, times(1)).delete(projectId);
    }
}
