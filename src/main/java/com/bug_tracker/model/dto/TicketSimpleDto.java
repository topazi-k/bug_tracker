package com.bug_tracker.model.dto;

import lombok.Data;

@Data
public class TicketSimpleDto {
    
    private long id;
    private String title;
    private String fullDescription;
    
}
