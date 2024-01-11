package com.unibuc.appbackend.services;

import com.unibuc.appbackend.entities.Sprint;
import com.unibuc.appbackend.exceptions.SprintNotFoundException;
import com.unibuc.appbackend.interfaces.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SprintService {

    private SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint create(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public Sprint getSprintById(UUID uuid) {
        Optional<Sprint> sprint = sprintRepository.findById(uuid);
        if (sprint.isPresent()) {
            return sprint.get();
        } else {
            throw new SprintNotFoundException();
        }
    }
}
