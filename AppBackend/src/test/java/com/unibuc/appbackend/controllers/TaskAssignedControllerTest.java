package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.controllers.TaskAssignedController;
import com.unibuc.appbackend.services.TaskAssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = TaskAssignedController.class)
public class TaskAssignedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskAssignedService taskAssignedService;
}
