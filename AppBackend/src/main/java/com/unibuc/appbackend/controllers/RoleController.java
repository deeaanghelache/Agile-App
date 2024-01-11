package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController("/role")
@Tag(name = "Role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
