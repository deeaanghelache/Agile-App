package com.unibuc.appbackend.services;

import com.unibuc.appbackend.interfaces.SprintRepository;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

    private SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }
}
