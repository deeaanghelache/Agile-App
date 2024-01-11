package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.UserRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userRole")
@Tag(name = "User - Role")
public class UserRoleController {

    private UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
}
