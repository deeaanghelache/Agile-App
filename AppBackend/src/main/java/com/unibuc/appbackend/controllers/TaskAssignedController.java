package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.services.TaskAssignedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/task")
@Tag(name = "Task")
public class TaskAssignedController {

    private TaskAssignedService taskAssignedService;

    public TaskAssignedController(TaskAssignedService taskAssignedService) {
        this.taskAssignedService = taskAssignedService;
    }

    @PostMapping("/createTask/{userId}/{projectId}/{sprintId}")
    @Operation(summary = "Assign a task to a user", description = "Assign a task to a user, providing the user id, project id, sprint id and description of the task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created and assigned successfully"),
            @ApiResponse(responseCode = "404", description = "UserId or ProjectId or SprintId not found!")
    })
    public ResponseEntity<TaskAssigned> create(@PathVariable("userId") UUID userId,
                                               @PathVariable("projectId") UUID projectId,
                                               @PathVariable("sprintId") UUID sprintId,
                                               @RequestBody String description) {
        return ResponseEntity.ok(taskAssignedService.create(userId, projectId, sprintId, description));
    }
}
