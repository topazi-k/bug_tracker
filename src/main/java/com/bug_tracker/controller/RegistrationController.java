package com.bug_tracker.controller;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserDto;
import com.bug_tracker.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private ApplicationEventPublisher eventPublisher;
    private ModelMapper modelMapper;

    public RegistrationController(RegistrationService userService, ApplicationEventPublisher eventPublisher,
                                  ModelMapper modelMapper) {
        this.registrationService = userService;
        this.eventPublisher = eventPublisher;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = registrationService.create(user);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }
}
