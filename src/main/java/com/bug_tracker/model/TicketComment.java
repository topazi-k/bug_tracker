package com.bug_tracker.model;

import com.bug_tracker.service.jsonserializer.UserCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_comments")
@Data
public class TicketComment {

    @Id
    @SequenceGenerator(name = "commentSequence", sequenceName = "ticket_comments_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentSequence")
    private long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonSerialize(using = UserCustomSerializer.class)
    private User createdBy;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicketComment other = (TicketComment) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TicketComment [id=" + id + ", comment=" + comment + ", createdAt=" + createdAt + ", createdBy="
                + createdBy + "]";
    }

}
