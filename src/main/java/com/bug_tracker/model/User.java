package com.bug_tracker.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String eMail;
    private UserRole role;
    private LocalDate createdAt;

    public enum UserRole {
        PROJECT_MANAGER, DEVELOPER, TESTER
    }

}
