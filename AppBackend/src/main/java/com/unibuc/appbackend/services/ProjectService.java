package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Project;
import com.unibuc.appbackend.exceptions.ProjectNotFoundException;
import com.unibuc.appbackend.interfaces.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(UUID uuid) {
        Optional<Project> project = projectRepository.findById(uuid);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new ProjectNotFoundException();
        }
    }
}
