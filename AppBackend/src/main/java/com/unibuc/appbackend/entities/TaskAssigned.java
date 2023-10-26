package com.unibuc.appbackend.entities;

import com.unibuc.enums.TaskAssignedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssigned {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String taskAssignedId;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    @Column()
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
}
