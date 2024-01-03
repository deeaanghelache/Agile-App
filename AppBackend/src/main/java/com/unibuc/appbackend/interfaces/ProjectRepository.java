package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
