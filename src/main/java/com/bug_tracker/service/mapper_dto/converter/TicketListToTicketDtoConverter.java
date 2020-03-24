package com.bug_tracker.service.mapper_dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.dto.TicketDto;

public class TicketListToTicketDtoConverter implements Converter<List<Ticket>,List<TicketDto>>{

    @Override
    public List<TicketDto> convert(MappingContext<List<Ticket>, List<TicketDto>> context) {
        List<Ticket> tickets =context.getSource();
        List<TicketDto> ticketsDto=new ArrayList<>();
        for(Ticket ticket: tickets) {
            TicketDto ticketDto=new TicketDto();
            ticketDto.setId(ticket.getId());
            ticketDto.setTitle(ticket.getTitle());
            ticketDto.setFullDescription(ticket.getFullDescription());
            ticketDto.setStatus(ticket.getStatus());
            ticketDto.setCategory(ticket.getCategory());
            ticketDto.setCreatedAt(ticket.getCreatedAt());
            ticketsDto.add(ticketDto);
        }
        return ticketsDto;
    }

   

}
