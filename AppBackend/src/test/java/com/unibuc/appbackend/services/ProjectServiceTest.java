package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.exceptions.ProjectNotFoundException;
import com.unibuc.appbackend.interfaces.ProjectRepository;
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
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    void createProject() {
        Project inputProject = new Project();
        inputProject.setName("Sample Project");
        inputProject.setDescription("This is a sample project.");

        Project savedProject = new Project();
        savedProject.setProjectId(UUID.randomUUID());
        savedProject.setName(inputProject.getName());
        savedProject.setDescription(inputProject.getDescription());

        when(projectRepository.save(inputProject)).thenReturn(savedProject);

        Project result = projectService.create(inputProject);

        assertNotNull(result);
        assertEquals(savedProject.getProjectId(), result.getProjectId());
        assertEquals(savedProject.getName(), result.getName());
        assertEquals(savedProject.getDescription(), result.getDescription());

        verify(projectRepository).save(inputProject);
    }

    @Test
    void getProjectById_existingProject_shouldReturnProject() {
        UUID projectId = UUID.randomUUID();
        Project expectedProject = new Project(projectId, "Sample Project", "This is a sample project.");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(expectedProject));

        Project result = projectService.getProjectById(projectId);

        assertNotNull(result);
        assertEquals(expectedProject.getProjectId(), result.getProjectId());
        assertEquals(expectedProject.getName(), result.getName());
        assertEquals(expectedProject.getDescription(), result.getDescription());

        verify(projectRepository).findById(projectId);
    }

    @Test
    void getProjectById_nonexistentProject_shouldThrowProjectNotFoundException() {
        UUID nonExistentProjectId = UUID.randomUUID();

        when(projectRepository.findById(nonExistentProjectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(nonExistentProjectId));

        verify(projectRepository).findById(nonExistentProjectId);
    }

    @Test
    void delete_existingProject_shouldDeleteProject() {
        UUID projectId = UUID.randomUUID();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(new Project()));

        projectService.delete(projectId);

        verify(projectRepository).findById(projectId);
        verify(projectRepository).deleteById(projectId);
    }

    @Test
    void delete_nonexistentProject_shouldThrowProjectNotFoundException() {
        UUID nonExistentProjectId = UUID.randomUUID();

        when(projectRepository.findById(nonExistentProjectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.delete(nonExistentProjectId));

        verify(projectRepository).findById(nonExistentProjectId);
        verifyNoMoreInteractions(projectRepository);
    }
}
