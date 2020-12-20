package com.bug_tracker.controller;

import com.bug_tracker.model.TicketComment;
import com.bug_tracker.service.TicketCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("projects/{id}/tickets/{ticketId}/comments")
public class TicketCommentController {

    TicketCommentService commentService;

    public TicketCommentController(TicketCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<TicketComment>> findByTicket(@PathVariable long ticketId) {
        List<TicketComment> comments = commentService.findByTicket(ticketId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<TicketComment>> create(@PathVariable long ticketId, @RequestBody TicketComment comment) {
        List<TicketComment> comments = commentService.create(ticketId, comment);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TicketComment> update(@RequestBody TicketComment ticketComment) {
        TicketComment comment = commentService.update(ticketComment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        commentService.delete(id);
    }
}
