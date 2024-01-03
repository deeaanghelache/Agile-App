package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.services.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sprint")
public class SprintController {

    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/create")
    public ResponseEntity<Sprint> create(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.create(sprint));
    }
}
