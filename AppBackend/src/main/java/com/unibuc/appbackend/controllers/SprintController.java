package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.services.SprintService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sprint")
public class SprintController {

    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }
}
