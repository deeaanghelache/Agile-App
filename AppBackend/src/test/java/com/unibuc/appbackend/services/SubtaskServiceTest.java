package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Subtask;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.interfaces.SubtaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubtaskServiceTest {

    @InjectMocks
    SubtaskService subtaskService;

    @Mock
    SubtaskRepository subtaskRepository;

    @Mock
    private UserService userService;

    @Mock
    private TaskAssignedService taskAssignedService;

    @Test
    void createSubtask_shouldReturnCreatedSubtask() {
        UUID userId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        String description = "Subtask Description";

        User user = new User(userId, "John", "Doe", "john.doe@example.com", "password");
        TaskAssigned taskAssigned = new TaskAssigned(taskId, "Task Description", TaskAssignedStatus.TO_DO);

        Subtask expectedSubtask = new Subtask();
        expectedSubtask.setUser(user);
        expectedSubtask.setDescription(description);
        expectedSubtask.setTaskAssigned(taskAssigned);
        expectedSubtask.setStatus(TaskAssignedStatus.TO_DO);

        when(userService.getUserById(userId)).thenReturn(user);
        when(taskAssignedService.getTaskById(taskId)).thenReturn(taskAssigned);
        when(subtaskRepository.save(any(Subtask.class))).thenReturn(expectedSubtask);

        Subtask result = subtaskService.create(userId, taskId, description);

        assertNotNull(result);
        assertEquals(expectedSubtask.getUser(), result.getUser());
        assertEquals(expectedSubtask.getDescription(), result.getDescription());
        assertEquals(expectedSubtask.getTaskAssigned(), result.getTaskAssigned());
        assertEquals(expectedSubtask.getStatus(), result.getStatus());

        verify(userService).getUserById(userId);
        verify(taskAssignedService).getTaskById(taskId);
        verify(subtaskRepository).save(any(Subtask.class));
    }
}
