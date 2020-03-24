package com.bug_tracker.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TicketCommentDto {
    private long id;
    private String comment;
    private LocalDateTime createdAt;
    private UserDto createdBy;
    
}
