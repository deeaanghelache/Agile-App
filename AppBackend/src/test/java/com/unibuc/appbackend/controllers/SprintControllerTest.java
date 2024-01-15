package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.controllers.SprintController;
import com.unibuc.appbackend.dtos.SprintRequest;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.services.SprintService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.UUID;

@WebMvcTest(controllers = SprintController.class)
public class SprintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SprintService sprintService;

    @Test
    public void createSprint() throws Exception {
        SprintRequest sprintRequest = new SprintRequest(LocalDate.now());

        when(sprintService.create(any())).thenReturn(new Sprint(UUID.randomUUID(), LocalDate.now()));

        mockMvc.perform(post("/sprint/createSprint")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sprintRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deadline").value(LocalDate.now().toString()));
    }
}
