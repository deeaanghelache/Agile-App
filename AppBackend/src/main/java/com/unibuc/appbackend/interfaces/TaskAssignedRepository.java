package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.TaskAssigned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignedRepository extends JpaRepository<TaskAssigned, String> {
}
