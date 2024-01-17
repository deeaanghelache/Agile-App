package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Subtask;
import com.unibuc.appbackend.entities.TaskAssigned;
import com.unibuc.appbackend.entities.User;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import com.unibuc.appbackend.exceptions.SubtaskNotFoundException;
import com.unibuc.appbackend.interfaces.SubtaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void updateStatus_shouldReturnUpdatedSubtask() {
        UUID subtaskId = UUID.randomUUID();
        String status = "done";

        Subtask existingSubtask = new Subtask(subtaskId, "Description", TaskAssignedStatus.TO_DO, null, null);
        Subtask updatedSubtask = new Subtask(subtaskId, "Description", TaskAssignedStatus.DONE, null, null);

        when(subtaskRepository.findById(subtaskId)).thenReturn(Optional.of(existingSubtask));
        when(subtaskRepository.save(existingSubtask)).thenReturn(updatedSubtask);

        Subtask result = subtaskService.updateStatus(subtaskId, status);

        assertNotNull(result);
        assertEquals(updatedSubtask, result);
        assertEquals(TaskAssignedStatus.DONE, result.getStatus());

        verify(subtaskRepository).findById(subtaskId);
        verify(subtaskRepository).save(existingSubtask);
    }

    @Test
    void updateStatus_shouldThrowSubtaskNotFoundException() {
        UUID subtaskId = UUID.randomUUID();
        String status = "done";

        when(subtaskRepository.findById(subtaskId)).thenReturn(Optional.empty());

        assertThrows(SubtaskNotFoundException.class, () -> subtaskService.updateStatus(subtaskId, status));

        verify(subtaskRepository).findById(subtaskId);
        verify(subtaskRepository, never()).save(any());
    }
}
