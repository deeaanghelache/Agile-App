package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.interfaces.TaskAssignedRepository;
import org.springframework.stereotype.Service;

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

    public TaskAssigned create(String userId, String projectId, String sprintId, String description) {
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
}
