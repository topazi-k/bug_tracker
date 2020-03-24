package com.bug_tracker.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.bug_tracker.model.enums.TicketPriority;
import com.bug_tracker.model.enums.TicketStatus;
import com.bug_tracker.model.enums.TicketType;

import lombok.Data;

@Data
public class TicketDto {
    private long id;
    private String title;
    private String fullDescription;
    private LocalDateTime createdAt;
    private List<TicketCommentDto> comments;
    private UserDto createdBy;
    private List<UserDto> assignedUsers;
    private TicketType category;
    private TicketPriority priority;
    private TicketStatus status;
    
}
