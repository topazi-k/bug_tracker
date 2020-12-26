package com.bug_tracker.model.dto;

import java.time.LocalDate;

import com.bug_tracker.model.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createdAt;
    
}
