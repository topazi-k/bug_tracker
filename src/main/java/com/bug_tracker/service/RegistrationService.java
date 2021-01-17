package com.bug_tracker.service;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserRegistrationDto;
import com.bug_tracker.repository.spring_data.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    private UserRepository userRepo;


    public RegistrationService(UserRepository userRepo) {
        this.userRepo = userRepo;

    }

    public UserRegistrationDto create(User user) {

        return null;
    }

}
