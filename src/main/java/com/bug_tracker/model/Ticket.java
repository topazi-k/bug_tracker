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
    private User userIdentifiedBy;;
    private List<User> assignedUsers;
    private List<String> subtasks;
    private LocalDate targetResolutionDate;
    private LocalDate actualResolutionDate;

    private Category category;
    private Priority priority;
    private Status status;
    private String statusComment;

    public enum Priority {
        TRIVIAL, MINOR, MAJOR, CRITICAL
    }

    public enum Status {
        UNASSIGNED, ASSIGNED, RESOLVED, CLOSED, REOPENED
    }

    public enum Category {
        BUG, TASK
    }
}
