package com.bug_tracker.service;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.TicketComment;
import com.bug_tracker.repository.spring_data.TicketCommentRepository;
import com.bug_tracker.repository.spring_data.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCommentService {

    private TicketCommentRepository commentRepository;
    private TicketRepository ticketRepository;

    public TicketCommentService(TicketCommentRepository commentRepository, TicketRepository ticketRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<TicketComment> findByTicket(long ticketId) {
        return commentRepository.findByTicket(ticketId);
    }


    //TODO: find better solution. maybe add ticketId property to TicketComment
    public List<TicketComment> create(long ticketId, TicketComment comment) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.addComment(comment);
        ticketRepository.save(ticket);
        return findByTicket(ticketId);
    }

    public TicketComment update(TicketComment updatedComment) {
        TicketComment comment = commentRepository.findById(updatedComment.getId()).get();
        comment.setComment(updatedComment.getComment());
        return commentRepository.save(comment);
    }

    public void delete(long id) {
        commentRepository.deleteById(id);
    }
}
