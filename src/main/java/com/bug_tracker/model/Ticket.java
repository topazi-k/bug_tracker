package com.bug_tracker.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Ticket {

    private long id;
    private String title;
    private String fullDesctiption;
    private LocalDate createdAt;
    private List<TicketComment> comments;
    private User createdBy;;
    private List<User> assignedUsers;
    private List<String> subtasks;
    private LocalDate targetResolutionDate;
    private LocalDate actualResolutionDate;

    private TickerType category;
    private Priority priority;
    private Status status;

    public enum Priority {
        TRIVIAL, MINOR, MAJOR, CRITICAL
    }

    public enum Status {
        UNASSIGNED, ASSIGNED, RESOLVED, CLOSED, REOPENED
    }

    public enum TickerType {
        BUG, TASK
    }
}
