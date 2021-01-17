package com.bug_tracker.controller;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserDto;
import com.bug_tracker.service.registration.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private ApplicationEventPublisher eventPublisher;
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = registrationService.createUser(user);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping("/confirm_email")
    public ResponseEntity<String> confirmEmail(@PathParam("token") String token) {

        return null;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
