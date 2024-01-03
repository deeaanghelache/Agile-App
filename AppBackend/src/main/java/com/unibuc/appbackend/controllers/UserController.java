package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
