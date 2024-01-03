package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.TaskAssignedService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/task")
public class TaskAssignedController {

    private TaskAssignedService taskAssignedService;

    public TaskAssignedController(TaskAssignedService taskAssignedService) {
        this.taskAssignedService = taskAssignedService;
    }
}
