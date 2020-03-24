package com.bug_tracker.service.mapper_dto;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.dto.TicketSimpleDto;

@Component
public class TicketSimpleMap extends PropertyMap<Ticket, TicketSimpleDto>{

    @Override
    protected void configure() {
        // TODO Auto-generated method stub
        
    }
    
}
