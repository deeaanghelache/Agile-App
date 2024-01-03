package com.unibuc.appbackend.services;

import com.unibuc.appbackend.interfaces.UserProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService {

    private UserProjectRepository userProjectRepository;

    public UserProjectService(UserProjectRepository userProjectRepository) {
        this.userProjectRepository = userProjectRepository;
    }
}
