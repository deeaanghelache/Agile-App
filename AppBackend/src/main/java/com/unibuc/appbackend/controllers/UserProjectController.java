package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.UserProjectService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userProject")
public class UserProjectController {

    private UserProjectService userProjectService;

    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }
}
