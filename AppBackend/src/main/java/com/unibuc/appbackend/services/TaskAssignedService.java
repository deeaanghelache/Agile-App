package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.exceptions.TaskAssignedNotFoundException;
import com.unibuc.appbackend.interfaces.TaskAssignedRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskAssignedService {

    private TaskAssignedRepository taskAssignedRepository;
    private UserService userService;
    private SprintService sprintService;
    private ProjectService projectService;

    public TaskAssignedService(TaskAssignedRepository taskAssignedRepository, UserService userService, SprintService sprintService, ProjectService projectService) {
        this.taskAssignedRepository = taskAssignedRepository;
        this.userService = userService;
        this.sprintService = sprintService;
        this.projectService = projectService;
    }

    public TaskAssigned create(UUID userId, UUID projectId, UUID sprintId, String description) {
        TaskAssigned task = new TaskAssigned();
        User user = userService.getUserById(userId);
        Project project = projectService.getProjectById(projectId);
        Sprint sprint = sprintService.getSprintById(sprintId);

        task.setUser(user);
        task.setDescription(description);
        task.setProject(project);
        task.setSprint(sprint);
        task.setStatus(TaskAssignedStatus.TO_DO);

        return taskAssignedRepository.save(task);
    }

    public TaskAssigned getTaskById(UUID uuid) {
        Optional<TaskAssigned> taskAssigned = taskAssignedRepository.findById(uuid);
        if(taskAssigned.isPresent()) {
            return taskAssigned.get();
        } else {
            throw new TaskAssignedNotFoundException();
        }
    }

    public TaskAssigned updateStatus(UUID taskId, String status) {
        Optional<TaskAssigned> taskAssigned = taskAssignedRepository.findById(taskId);
        if(taskAssigned.isPresent()) {
            TaskAssigned task = taskAssigned.get();
            TaskAssignedStatus newStatus = switch (status.toLowerCase()) {
                case "todo" -> TaskAssignedStatus.TO_DO;
                case "inprogress" -> TaskAssignedStatus.IN_PROGRESS;
                case "done" -> TaskAssignedStatus.DONE;
                case "nicetohave" -> TaskAssignedStatus.NICE_TO_HAVE;
                default -> null;
            };

            task.setStatus(newStatus);
            return taskAssignedRepository.save(task);
        } else {
            throw new TaskAssignedNotFoundException();
        }
    }
}
