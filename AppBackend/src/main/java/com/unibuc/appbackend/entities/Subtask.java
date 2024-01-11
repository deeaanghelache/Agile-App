package com.unibuc.appbackend.entities;

import jakarta.persistence.*;
import com.unibuc.appbackend.enums.TaskAssignedStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID subtaskId;

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
