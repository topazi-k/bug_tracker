package com.bug_tracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bug_tracker.model.enums.TicketPriority;
import com.bug_tracker.model.enums.TicketStatus;
import com.bug_tracker.model.enums.TicketType;

import lombok.Data;

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
    private String fullDesctiption;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private List<TicketComment> comments;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private List<TicketLog> logs;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ticket_assigned_users", joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> assignedUsers;
    
    @Column(name="target_date")
    private LocalDate targetResolutionDate;
    
    @Column(name="actual_date")
    private LocalDate actualResolutionDate;

    @Enumerated(EnumType.STRING)
    private TicketType category;
    
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        if(status==TicketStatus.CLOSED) {
            actualResolutionDate=LocalDate.now();
        }
    }

}
