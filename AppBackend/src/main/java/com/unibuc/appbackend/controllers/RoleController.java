package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
