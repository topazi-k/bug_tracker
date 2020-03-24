package com.bug_tracker.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProjectDto {
    
    private long id;
    private String projectName;
    private String description;
    private List<UserDto> projectMembers;
    private List<TicketSimpleDto> tickets;
}
