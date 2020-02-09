package com.bug_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.TicketComment;
import com.bug_tracker.repository.spring_data.TicketCommentRepository;
import com.bug_tracker.repository.spring_data.TicketRepository;

@Service
public class TicketCommentService {
    
    private TicketCommentRepository commentRepository;
    private TicketRepository ticketRepository;
    
    public TicketCommentService(TicketCommentRepository commentRepository,TicketRepository ticketRepository) {
        this.commentRepository=commentRepository;
        this.ticketRepository=ticketRepository;
    }
    
    public List<TicketComment> findByTicket(long ticketId){
        return commentRepository.findByTicket(ticketId);
    }
    
    
    //TODO: find better solution. maybe add ticketId property to TicketComment
    public List<TicketComment> create(long ticketId, TicketComment comment){
        Ticket ticket=ticketRepository.findById(ticketId).get();
        commentRepository.save(comment);
        ticket.addComment(comment);
        ticketRepository.save(ticket);
        return findByTicket(ticketId);
    }
}
