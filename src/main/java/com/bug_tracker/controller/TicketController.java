package com.bug_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.Ticket;

@RestController()
@RequestMapping("/projects")
public class TicketController {
    
    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Project project){
        return null;                                                     ////change when create service method create
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id){
        
        return null;
    }
    
    @GetMapping
    public ResponseEntity<List<Ticket>> getAll(){
        
        return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody Project project){
        
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        
        
    }
}
