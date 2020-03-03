package com.bug_tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAngularController {
    
@GetMapping
public ResponseEntity<UserT> get(){
    return new ResponseEntity<>(new UserT(),HttpStatus.OK);
    
}
}
