package com.bug_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.User;

@RestController
public class UserController {

    @PostMapping
    public ResponseEntity<User> create(@RequestBody Project project){
        return null;                                                     ////change when create service method create
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        
        return null;
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody Project project){
        
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        
        
    }
}
