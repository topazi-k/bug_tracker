package com.bug_tracker.model;

import com.bug_tracker.service.jsonserializer.TicketCustomSerializer;
import com.bug_tracker.service.jsonserializer.UserCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @SequenceGenerator(name = "projectSequence", sequenceName = "projects_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectSequence")
    private long id;

    @Column(name = "name")
    private String projectName;

    @Column(name = "descript")
    private String description;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "projects_users", joinColumns = {@JoinColumn(name = "project_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonSerialize(contentUsing = UserCustomSerializer.class)
    private Set<User> projectMembers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonSerialize(contentUsing = TicketCustomSerializer.class)
    private Set<Ticket> tickets = new HashSet<>();

    public void addUser(User user) {
        projectMembers.add(user);
    }

    public void removeUser(User user) {
        projectMembers.remove(user);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", projectName=" + projectName + ", descripton=" + description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
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
}
