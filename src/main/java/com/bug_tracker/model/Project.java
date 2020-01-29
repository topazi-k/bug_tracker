package com.bug_tracker.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Project {

    private long id;
    private String projectName;
    private String descripton;
    private List<User> projectMembers;
    private List<Ticket> tickets;
}
