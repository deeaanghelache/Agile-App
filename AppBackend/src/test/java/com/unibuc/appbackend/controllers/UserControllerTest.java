package com.unibuc.appbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.appbackend.controllers.UserController;
import com.unibuc.appbackend.dtos.UserRequest;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void create() throws Exception {
        UserRequest userRequest = new UserRequest("John", "Allan", "john.allan@gmail.com", "password123");

        when(userService.create(any())).thenReturn(new User(UUID.randomUUID(), "John", "Allan", "john.allan@gmail.com", "password123"));

        mockMvc.perform(post("/user/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(userRequest.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userRequest.getLastName()))
                .andExpect(jsonPath("$.email").value(userRequest.getEmail()))
                .andExpect(jsonPath("$.password").value(userRequest.getPassword()));
    }

//    @Test
//    public void login() throws Exception {
//        User user = new User(null, null, null, "john.allan@gmail.com", "password123", null, null, null, null);
//        User expectedUser = new User(UUID.randomUUID(), "John", "Allan", "john.allan@gmail.com", "password123", null, null, null, null);
//
//        when(userService.login(user)).thenReturn(expectedUser);
//
//        String jsonString = objectMapper.writeValueAsString(user);
//        System.out.println("JSON String: " + jsonString);
//
//        // Error: -> java.lang.AssertionError: No value at JSON path "$.password" ???
//        mockMvc.perform(post("/user/login")
//                        .contentType("application/json")
//                        .content(jsonString))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.password").value(expectedUser.getUserId()))
//                .andExpect(jsonPath("$.email").value(expectedUser.getEmail()));
//    }

    @Test
    public void getUserById() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(userService.getUserById(any())).thenReturn(new User(uuid, "John", "Allan", "john.allan@gmail.com", "password123"));

        mockMvc.perform(get("/user/getById/{id}", uuid).contentType("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Allan"))
                .andExpect(jsonPath("$.email").value("john.allan@gmail.com"))
                .andExpect(jsonPath("$.password").value("password123"));

    }

    @Test
    public void deleteUser() throws Exception {
        UUID uuid = UUID.randomUUID();

        mockMvc.perform(delete("/user/delete/{id}", uuid).contentType("application/json"))
                .andExpect(status().isOk());

        verify(userService, times(1)).delete(uuid);
    }

}
