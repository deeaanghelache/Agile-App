package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.embeddedIds.UserProjectEmbeddedId;
import com.unibuc.appbackend.entities.*;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.services.SubtaskService;
import com.unibuc.appbackend.services.UserProjectService;
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

import java.util.UUID;
@WebMvcTest(controllers = UserProjectController.class)
public class UserProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserProjectService userProjectService;

    @Test
    public void createUserProject() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        User user = new User(userId, "User", "1", "user@gmail.com", "parola");
        Project project = new Project(projectId, "Project1", "Description1");

        UserProjectEmbeddedId userProjectEmbeddedId = new UserProjectEmbeddedId(projectId, userId);
        UserProject userProject = new UserProject(userProjectEmbeddedId, user, project);

        when(userProjectService.create(userId, projectId)).thenReturn(userProject);

        mockMvc.perform(post("/userProject/createUserProject/{userId}/{projectId}", userId, projectId)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userProjectEmbeddedId.projectId").value(userProjectEmbeddedId.getProjectId().toString()))
                .andExpect(jsonPath("$.userProjectEmbeddedId.userId").value(userProjectEmbeddedId.getUserId().toString()));
    }

}
