package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.exceptions.TaskAssignedNotFoundException;
import com.unibuc.appbackend.services.TaskAssignedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void updateStatus_shouldReturnUpdatedTaskAssigned() throws Exception {
        UUID taskId = UUID.randomUUID();
        String status = "done";

        TaskAssigned updatedTaskAssigned = new TaskAssigned();
        updatedTaskAssigned.setTaskAssignedId(taskId);
        updatedTaskAssigned.setDescription("Updated Description");
        updatedTaskAssigned.setStatus(TaskAssignedStatus.DONE);

        when(taskAssignedService.updateStatus(taskId, status)).thenReturn(updatedTaskAssigned);

        mockMvc.perform(put("/task/updateStatus/{taskId}/{status}", taskId, status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskAssignedId").value(updatedTaskAssigned.getTaskAssignedId().toString()))
                .andExpect(jsonPath("$.description").value(updatedTaskAssigned.getDescription()))
                .andExpect(jsonPath("$.status").value(updatedTaskAssigned.getStatus().toString()));

       verify(taskAssignedService).updateStatus(taskId, status);
    }

    @Test
    void updateStatus_shouldReturnNotFound() throws Exception {
        UUID taskId = UUID.randomUUID();
        String status = "done";

        when(taskAssignedService.updateStatus(taskId, status))
                .thenThrow(new TaskAssignedNotFoundException());

        mockMvc.perform(put("/task/updateStatus/{taskId}/{status}", taskId, status))
                .andExpect(status().isNotFound());

        verify(taskAssignedService).updateStatus(taskId, status);
    }
}
