package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/project")
@Tag(name = "Project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/createProject")
    @Operation(summary = "Create a project", description = "Create a new project based on the information received in the request's body")
    @ApiResponse(responseCode = "201", description = "Project created successfully")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.create(project));
    }
}
