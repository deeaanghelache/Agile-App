package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.SubtaskService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/subtask")
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }
}
