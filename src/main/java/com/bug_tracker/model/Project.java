package com.bug_tracker.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="projects")
@Data
public class Project {
    
    @Id
    private long id;
    private String projectName;
    private String descripton;
    private List<User> projectMembers;
    private List<Ticket> tickets;
}
