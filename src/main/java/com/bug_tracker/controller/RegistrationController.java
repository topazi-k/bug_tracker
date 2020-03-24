package com.bug_tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug_tracker.model.User;
import com.bug_tracker.model.UserRole;
import com.bug_tracker.service.UserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    
 //   public PasswordEncoder passwordEn;
       

    private UserService userService;

    public RegistrationController(UserService userService/*, PasswordEncoder passwordEncoder*/) {
        this.userService = userService;
       // this.passwordEn=passwordEncoder;
       
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
     //   String encodedPassword = passwordEn.encode(user.getPassword());
        UserRole role=new UserRole();
        role.setId(1);
        role.setRole("ROLE_ADMIN");
        user.setRole(role);
     //   user.setPassword(encodedPassword);
        user = userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED); //// change when create service method create
    }
}
