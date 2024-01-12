package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.UserProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/userProject")
@Tag(name = "User - Project")
public class UserProjectController {

    private UserProjectService userProjectService;

    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }
}
