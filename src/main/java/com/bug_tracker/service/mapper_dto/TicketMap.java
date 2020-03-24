package com.bug_tracker.service.mapper_dto;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.dto.TicketDto;

@Component
public class TicketMap extends PropertyMap<Ticket, TicketDto>{

    @Override
    protected void configure() {
       // using(new UserListToUserDtoConverter()).map(source.getAssignedUsers()).setAssignedUsers(null);
        
    }
    
}
