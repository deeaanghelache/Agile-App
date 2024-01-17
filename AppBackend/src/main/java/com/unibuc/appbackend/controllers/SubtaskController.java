package com.unibuc.appbackend.controllers;

import com.unibuc.appbackend.entities.Subtask;
import com.unibuc.appbackend.services.SubtaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/subtask")
@Tag(name = "Subtask")
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @PostMapping("/createSubtask/{userId}/{taskId}")
    @Operation(summary = "Assign a subtask to a user", description = "Assign a subtask to a user, providing the user id, task id and description of the subtask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subask created and assigned successfully"),
            @ApiResponse(responseCode = "404", description = "UserId or TaskId not found!")
    })
    public ResponseEntity<Subtask> create(@PathVariable("userId") @Parameter(description = "Id of the user assigned to this task") UUID userId,
                                          @PathVariable("taskId") @Parameter(description = "Id of the task this subtask is a part of") UUID taskId,
                                          @RequestBody String description) {
        return ResponseEntity.ok(subtaskService.create(userId, taskId, description));
    }

    @PutMapping("/updateStatus/{subtaskId}/{status}")
    @Operation(summary = "Update status of subtask", description = "Update the status of a given subtask by providing the subtask id and the new status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subtask status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Subtask not found!")
    })
    public ResponseEntity<Subtask> updateStatus(@PathVariable("subtaskId") UUID subtaskId, @PathVariable("status") String status) {
        return ResponseEntity.ok(subtaskService.updateStatus(subtaskId, status));
    }
}
