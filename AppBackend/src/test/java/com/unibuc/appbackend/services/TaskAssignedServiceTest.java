package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.exceptions.TaskAssignedNotFoundException;
import com.unibuc.appbackend.interfaces.TaskAssignedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskAssignedServiceTest {

    @InjectMocks
    private TaskAssignedService taskAssignedService;

    @Mock
    private TaskAssignedRepository taskAssignedRepository;

    @Mock
    private UserService userService;

    @Mock
    private SprintService sprintService;

    @Mock
    private ProjectService projectService;

    @Test
    void createTaskAssigned_shouldReturnCreatedTaskAssigned() {
        UUID userId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        UUID sprintId = UUID.randomUUID();
        String description = "Task Description";

        User user = new User(userId, "John", "Doe", "john.doe@example.com", "password");
        Project project = new Project(projectId, "Project Name", "Project Description");
        Sprint sprint = new Sprint(sprintId, LocalDate.now());

        TaskAssigned expectedTaskAssigned = new TaskAssigned();
        expectedTaskAssigned.setUser(user);
        expectedTaskAssigned.setDescription(description);
        expectedTaskAssigned.setProject(project);
        expectedTaskAssigned.setSprint(sprint);
        expectedTaskAssigned.setStatus(TaskAssignedStatus.TO_DO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(projectService.getProjectById(projectId)).thenReturn(project);
        when(sprintService.getSprintById(sprintId)).thenReturn(sprint);
        when(taskAssignedRepository.save(any(TaskAssigned.class))).thenReturn(expectedTaskAssigned);

        TaskAssigned result = taskAssignedService.create(userId, projectId, sprintId, description);

        assertNotNull(result);
        assertEquals(expectedTaskAssigned.getUser(), result.getUser());
        assertEquals(expectedTaskAssigned.getDescription(), result.getDescription());
        assertEquals(expectedTaskAssigned.getProject(), result.getProject());
        assertEquals(expectedTaskAssigned.getSprint(), result.getSprint());
        assertEquals(expectedTaskAssigned.getStatus(), result.getStatus());

        verify(userService).getUserById(userId);
        verify(projectService).getProjectById(projectId);
        verify(sprintService).getSprintById(sprintId);
        verify(taskAssignedRepository).save(any(TaskAssigned.class));
    }

    @Test
    void getTaskById_existingTaskAssigned_shouldReturnTaskAssigned() {
        UUID taskId = UUID.randomUUID();
        TaskAssigned expectedTaskAssigned = new TaskAssigned(taskId, "Task Description", TaskAssignedStatus.TO_DO);

        when(taskAssignedRepository.findById(taskId)).thenReturn(Optional.of(expectedTaskAssigned));

        TaskAssigned result = taskAssignedService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals(expectedTaskAssigned.getTaskAssignedId(), result.getTaskAssignedId());
        assertEquals(expectedTaskAssigned.getDescription(), result.getDescription());
        assertEquals(expectedTaskAssigned.getStatus(), result.getStatus());

        verify(taskAssignedRepository).findById(taskId);
    }

    @Test
    void getTaskById_nonexistentTaskAssigned_shouldThrowTaskAssignedNotFoundException() {
        UUID nonExistentTaskId = UUID.randomUUID();

        when(taskAssignedRepository.findById(nonExistentTaskId)).thenReturn(Optional.empty());

        assertThrows(TaskAssignedNotFoundException.class, () -> taskAssignedService.getTaskById(nonExistentTaskId));

        verify(taskAssignedRepository).findById(nonExistentTaskId);
    }

}
