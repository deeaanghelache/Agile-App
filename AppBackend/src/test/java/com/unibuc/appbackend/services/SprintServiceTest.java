package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.exceptions.SprintNotFoundException;
import com.unibuc.appbackend.interfaces.SprintRepository;
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
public class SprintServiceTest {

    @InjectMocks
    private SprintService sprintService;

    @Mock
    private SprintRepository sprintRepository;

    @Test
    void createSprint_shouldReturnCreatedSprint() {
        Sprint expectedSprint = new Sprint();
        expectedSprint.setSprintId(UUID.randomUUID());
        expectedSprint.setDeadline(LocalDate.now());

        when(sprintRepository.save(any(Sprint.class))).thenReturn(expectedSprint);

        Sprint result = sprintService.create(expectedSprint);

        assertNotNull(result);
        assertEquals(expectedSprint.getSprintId(), result.getSprintId());
        assertEquals(expectedSprint.getDeadline(), result.getDeadline());

        verify(sprintRepository).save(any(Sprint.class));
    }

    @Test
    void getSprintById_existingSprint_shouldReturnSprint() {
        UUID sprintId = UUID.randomUUID();
        Sprint expectedSprint = new Sprint(sprintId, LocalDate.now());

        when(sprintRepository.findById(sprintId)).thenReturn(Optional.of(expectedSprint));

        Sprint result = sprintService.getSprintById(sprintId);

        assertNotNull(result);
        assertEquals(expectedSprint.getSprintId(), result.getSprintId());
        assertEquals(expectedSprint.getDeadline(), result.getDeadline());

        verify(sprintRepository).findById(sprintId);
    }

    @Test
    void getSprintById_nonexistentSprint_shouldThrowSprintNotFoundException() {
        UUID nonExistentSprintId = UUID.randomUUID();

        when(sprintRepository.findById(nonExistentSprintId)).thenReturn(Optional.empty());

        assertThrows(SprintNotFoundException.class, () -> sprintService.getSprintById(nonExistentSprintId));

        verify(sprintRepository).findById(nonExistentSprintId);
    }
}
