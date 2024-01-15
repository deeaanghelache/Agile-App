package com.unibuc.appbackend.entities;

import com.unibuc.appbackend.enums.TaskAssignedStatus;
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
public class TaskAssigned {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @NotNull
    private UUID taskAssignedId;

    @Column(columnDefinition = "varchar(1000)")
    @NotNull
    private String description;

    @Column()
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskAssignedStatus status;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("projectId")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @MapsId("sprintId")
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @OneToMany(mappedBy = "taskAssigned", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subtask> subtasks;

    public TaskAssigned(UUID taskAssignedId, String description, TaskAssignedStatus status) {
        this.taskAssignedId = taskAssignedId;
        this.description = description;
        this.status = status;
    }
}
