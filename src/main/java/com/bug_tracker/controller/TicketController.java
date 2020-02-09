package com.bug_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.service.TicketService;

@RestController()
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        ticket = ticketService.create(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> allTickets = ticketService.findAll();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket, @PathVariable long id) { /// ??
        ticketService.update(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    // TODO: how to be with delete return statatus
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }
    
    
}
