package com.bug_tracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bug_tracker.service.jsonserializer.TicketCustomSerializer;
import com.bug_tracker.service.jsonserializer.UserCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

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
    @JoinTable(name = "projects_users", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonSerialize(contentUsing =UserCustomSerializer.class)
    private List<User> projectMembers = new ArrayList<>();;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonSerialize(contentUsing=TicketCustomSerializer.class)
    private List<Ticket> tickets = new ArrayList<>();

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
