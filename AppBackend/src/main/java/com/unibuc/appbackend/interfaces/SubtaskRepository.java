package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubtaskRepository extends JpaRepository<Subtask, UUID> {
}
