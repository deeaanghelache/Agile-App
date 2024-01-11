package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.services.SprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sprint")
@Tag(name = "Sprint")
public class SprintController {

    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/createSprint")
    @Operation(summary = "Create a sprint", description = "Create a new sprint based on the information received in the request's body")
    @ApiResponse(responseCode = "201", description = "Sprint created successfully")
    public ResponseEntity<Sprint> create(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.create(sprint));
    }
}
