package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.services.TaskAssignedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/task")
public class TaskAssignedController {

    private TaskAssignedService taskAssignedService;

    public TaskAssignedController(TaskAssignedService taskAssignedService) {
        this.taskAssignedService = taskAssignedService;
    }

    @PostMapping("/createTask/{userId}/{projectId}/{sprintId}")
    public ResponseEntity<TaskAssigned> create(@PathVariable("userId") UUID userId,
                                               @PathVariable("projectId") UUID projectId,
                                               @PathVariable("sprintId") UUID sprintId,
                                               @RequestBody String description) {
        return ResponseEntity.ok(taskAssignedService.create(userId, projectId, sprintId, description));
    }
}
