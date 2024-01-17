package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.entities.Subtask;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.exceptions.SubtaskNotFoundException;
import com.unibuc.appbackend.services.SubtaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubtaskController.class)
public class SubtaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubtaskService subtaskService;

    @Test
    public void createSubtask() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();

        User user = new User(userId, "User", "1", "user@gmail.com", "parola");
        TaskAssigned taskAssigned = new TaskAssigned(taskId, "Description", TaskAssignedStatus.TO_DO);

        UUID subtaskId = UUID.randomUUID();
        String description = "Description";
        Subtask subtask = new Subtask(subtaskId, description, TaskAssignedStatus.TO_DO, user, taskAssigned);
        System.out.println(subtask);

        when(subtaskService.create(userId, taskId, description)).thenReturn(subtask);

        mockMvc.perform(post("/subtask/createSubtask/{userId}/{taskId}", userId, taskId)
                        .contentType("application/json")
                        .content(description))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(subtask.getDescription()));
//                .andExpect(jsonPath("$.user").value(subtask.getUser()))
//                .andExpect(jsonPath("$.taskAssigned").value(subtask.getTaskAssigned()));
    }

    @Test
    void updateStatus_shouldReturnUpdatedSubtask() throws Exception {
        UUID subtaskId = UUID.randomUUID();
        String status = "done";

        Subtask updatedSubtask = new Subtask(subtaskId, "Description", TaskAssignedStatus.DONE, null, null);

        when(subtaskService.updateStatus(subtaskId, status)).thenReturn(updatedSubtask);

        mockMvc.perform(put("/subtask/updateStatus/{subtaskId}/{status}", subtaskId, status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subtaskId").value(updatedSubtask.getSubtaskId().toString()))
                .andExpect(jsonPath("$.description").value(updatedSubtask.getDescription()))
                .andExpect(jsonPath("$.status").value(updatedSubtask.getStatus().toString()));

        verify(subtaskService).updateStatus(subtaskId, status);
    }

    @Test
    void updateStatus_shouldReturnNotFound() throws Exception {
        UUID subtaskId = UUID.randomUUID();
        String status = "done";

        when(subtaskService.updateStatus(subtaskId, status)).thenThrow(new SubtaskNotFoundException());

        mockMvc.perform(put("/subtask/updateStatus/{subtaskId}/{status}", subtaskId, status))
                .andExpect(status().isNotFound());

        verify(subtaskService).updateStatus(subtaskId, status);
    }
}
