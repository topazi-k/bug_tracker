package com.bug_tracker.model;

public class TicketLog {
    private long id;
    private String action;
    private Ticket.Status oldStatus;
    private Ticket.Status newStatus;
    private String message;
}
