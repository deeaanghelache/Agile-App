package com.unibuc.appbackend.interfaces;

import com.unibuc.appbackend.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, String> {
}
