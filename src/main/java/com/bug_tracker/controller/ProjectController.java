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

@RestController
public class ProjectController {

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project){
        return null;                                                     ////change when create service method create
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id){
        
        return null;
    }
    
    @GetMapping
    public ResponseEntity<List<Project>> getAll(){
        
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@RequestBody Project project){
        
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        
        
    }
}
