package com.bug_tracker.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class TicketComment {
    private long id;
    private String comment;
    private LocalDate createdAt;
    private User createdBy;
}
