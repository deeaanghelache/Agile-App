package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, String> {
}
