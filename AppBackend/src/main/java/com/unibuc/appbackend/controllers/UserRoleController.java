package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.UserRoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userRole")
public class UserRoleController {

    private UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
}
