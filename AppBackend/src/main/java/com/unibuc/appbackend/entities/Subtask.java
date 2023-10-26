package com.unibuc.appbackend.entities;

import jakarta.persistence.*;
import com.unibuc.enums.TaskAssignedStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String subtaskId;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    @Column()
    private TaskAssignedStatus status;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("taskAssignedId")
    @ManyToOne
    @JoinColumn(name = "task_assigned_id")
    private TaskAssigned taskAssigned;
}
