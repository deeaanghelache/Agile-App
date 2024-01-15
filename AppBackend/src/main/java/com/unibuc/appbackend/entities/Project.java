package com.unibuc.appbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @NotNull
    private UUID projectId;

    @Column(columnDefinition = "varchar(1000)")
    @NotNull
    private String name;

    @Column(columnDefinition = "varchar(1000)")
    @NotNull
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserProject> userProjects;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskAssigned> tasksAssigned;

    public Project(UUID projectId, String name, String description) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
    }
}
