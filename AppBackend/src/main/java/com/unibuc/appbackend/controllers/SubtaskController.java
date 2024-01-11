package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.SubtaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController("/subtask")
@Tag(name = "Subtask")
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }
}
