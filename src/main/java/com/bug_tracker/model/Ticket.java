package com.bug_tracker.model;

import com.bug_tracker.model.enums.TicketPriority;
import com.bug_tracker.model.enums.TicketStatus;
import com.bug_tracker.model.enums.TicketType;
import com.bug_tracker.service.jsonserializer.UserCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @SequenceGenerator(name = "ticketsSequence", sequenceName = "tickets_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketsSequence")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String fullDescription;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private List<TicketComment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private List<TicketLog> logs = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "created_by")
    @JsonSerialize(using = UserCustomSerializer.class)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ticket_assigned_users", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonSerialize(contentUsing = UserCustomSerializer.class)
    private List<User> assignedUsers = new ArrayList<>();

    @Column(name = "target_date")
    private LocalDate targetResolutionDate;

    @Column(name = "actual_date")
    private LocalDate actualResolutionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type")
    private TicketType category;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_priority")
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status")
    private TicketStatus status;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        if (status == TicketStatus.CLOSED) {
            actualResolutionDate = LocalDate.now();
        }
    }

    public void addComment(TicketComment comment) {
        comments.add(comment);
    }

}
