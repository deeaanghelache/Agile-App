package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.services.SubtaskService;
import com.unibuc.appbackend.services.TaskAssignedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.config.Task;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.UUID;

@WebMvcTest(controllers = TaskAssignedController.class)
public class TaskAssignedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskAssignedService taskAssignedService;

    @Test
    public void createTask() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        UUID sprintId = UUID.randomUUID();
        String description = "Sample task description";

        User user = new User(userId, "User", "1", "user@gmail.com", "parola");
        Project project = new Project(projectId, "Project 1", description);
        Sprint sprint = new Sprint(sprintId, LocalDate.now());
        TaskAssigned taskAssigned = new TaskAssigned(UUID.randomUUID(), description, TaskAssignedStatus.TO_DO, user, project, sprint, null);

        when(taskAssignedService.create(userId, projectId, sprintId, description)).thenReturn(taskAssigned);

        mockMvc.perform(post("/task/createTask/{userId}/{projectId}/{sprintId}", userId, projectId, sprintId)
                        .contentType("application/json")
                        .content(description))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(taskAssigned.getDescription()));
    }
}
