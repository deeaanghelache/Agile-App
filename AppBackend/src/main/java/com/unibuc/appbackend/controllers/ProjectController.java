package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.ProjectService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
}
