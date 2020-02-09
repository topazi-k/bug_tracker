package com.bug_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.repository.spring_data.TicketRepository;

@Service
public class TicketService {

    private TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket create(Ticket ticket) {
        return ticket = ticketRepo.save(ticket);
    }

    public Ticket findById(long id) {
        return ticketRepo.findById(id).get();
    }

    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    public Ticket update(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public void delete(long id) {
        ticketRepo.deleteById(id);
    }
}
