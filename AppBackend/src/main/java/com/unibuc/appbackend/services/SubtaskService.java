package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Subtask;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.interfaces.SubtaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubtaskService {

    private SubtaskRepository subtaskRepository;
    private UserService userService;
    private TaskAssignedService taskAssignedService;

    public SubtaskService(SubtaskRepository subtaskRepository, UserService userService, TaskAssignedService taskAssignedService) {
        this.subtaskRepository = subtaskRepository;
        this.userService = userService;
        this.taskAssignedService = taskAssignedService;
    }

    public Subtask create(UUID userId, UUID taskId, String description) {
        Subtask subtask = new Subtask();
        User user = userService.getUserById(userId);
        TaskAssigned taskAssigned = taskAssignedService.getTaskById(taskId);

        subtask.setUser(user);
        subtask.setDescription(description);
        subtask.setTaskAssigned(taskAssigned);
        subtask.setStatus(TaskAssignedStatus.TO_DO);

        return subtaskRepository.save(subtask);
    }
}
