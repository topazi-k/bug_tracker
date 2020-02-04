package com.bug_tracker.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_log")
public class TicketLog {

    @Id
    @SequenceGenerator(name = "logSequence", sequenceName = "ticket_log_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logSequence")
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersits() {
        createdAt = LocalDateTime.now();
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicketLog other = (TicketLog) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TicketLog [id=" + id + ", message=" + message + ", createdAt=" + createdAt + "]";
    }
    
}
