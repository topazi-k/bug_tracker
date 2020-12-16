package com.bug_tracker.service;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.Ticket;
import com.bug_tracker.repository.spring_data.ProjectRepository;
import com.bug_tracker.repository.spring_data.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepo;
    private ProjectRepository projectRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ProjectRepository projectRepository) {
        this.ticketRepo = ticketRepository;
        this.projectRepository = projectRepository;
    }

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Transactional
    public Ticket create(long projectId, Ticket ticket) {
        Project project = projectRepository.findById(projectId).get();
        ticket.setProject(project);
        ticketRepo.save(ticket);
        return ticketRepo.findById(ticket.getId()).get();
    }

    public Ticket findById(long id) {
        return ticketRepo.findById(id).get();
    }

    public List<Ticket> findAllByProject(int projectId) {
        return ticketRepo.findTicketsByProject_projectId(projectId);
    }

    public Ticket update(Ticket ticket, long id) {
        if (!ticketRepo.existsById(id)) {
            // TODO: not exist ticket exception
        }
        return ticketRepo.save(ticket);
    }

    public void delete(long id) {
        ticketRepo.deleteById(id);
    }
}
