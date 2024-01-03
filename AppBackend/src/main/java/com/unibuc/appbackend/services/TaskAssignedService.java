package com.unibuc.appbackend.services;

import com.unibuc.appbackend.interfaces.TaskAssignedRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignedService {

    private TaskAssignedRepository taskAssignedRepository;

    public TaskAssignedService(TaskAssignedRepository taskAssignedRepository) {
        this.taskAssignedRepository = taskAssignedRepository;
    }
}
