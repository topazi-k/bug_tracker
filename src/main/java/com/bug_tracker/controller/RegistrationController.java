package com.bug_tracker.controller;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserDto;
import com.bug_tracker.model.dto.UserRegistrationDto;
import com.bug_tracker.security.SecurityConstants;
import com.bug_tracker.service.registration.RegistrationEvent;
import com.bug_tracker.service.registration.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.bug_tracker.security.SecurityConstants.EMAIL_CONFIRM_URL;

@RestController
@RequestMapping("/users/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private ApplicationEventPublisher eventPublisher;
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserRegistrationDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = registrationService.createUser(user);
        eventPublisher.publishEvent(new RegistrationEvent(user, SecurityConstants.BASE_URL + EMAIL_CONFIRM_URL));
        return new ResponseEntity<>("Confirm your email ...", HttpStatus.CREATED);
    }

    @GetMapping("/confirm_email")
    public ResponseEntity<String> confirmEmail(@PathParam("token") String token) {
        registrationService.confirmEmail(token);
        return new ResponseEntity<>("Success registration",HttpStatus.ACCEPTED);
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
