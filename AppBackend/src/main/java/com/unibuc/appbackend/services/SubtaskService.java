package com.unibuc.appbackend.services;

import com.unibuc.appbackend.interfaces.SubtaskRepository;
import org.springframework.stereotype.Service;

@Service
public class SubtaskService {

    private SubtaskRepository subtaskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }
}
