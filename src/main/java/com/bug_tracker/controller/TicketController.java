package com.bug_tracker.controller;

import com.bug_tracker.model.Ticket;
import com.bug_tracker.model.dto.TicketDto;
import com.bug_tracker.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tickets")
public class TicketController {

    private TicketService ticketService;
    private ModelMapper modelMapper;

    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<TicketDto> create(@PathVariable long projectId, @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.create(projectId, convertToTicket(ticketDto));

        return new ResponseEntity<>(convertToTicketDto(ticket), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        return new ResponseEntity<>(convertToTicketDto(ticket), HttpStatus.OK);
    }

    // TODO : decide need or not?
    @GetMapping
    public ResponseEntity<List<TicketDto>> findAllByProject(@PathVariable int projectId) {
        List<Ticket> allTickets = ticketService.findAllByProject(projectId);
        List<TicketDto> allTicketsDto = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            TicketDto ticketDto = convertToTicketDto(ticket);
            allTicketsDto.add(ticketDto);
        }
        return new ResponseEntity<>(allTicketsDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket, @PathVariable long id) {
        ticketService.update(ticket, id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    // TODO: how to be with delete return statatus
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }


    private Ticket convertToTicket(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, Ticket.class);
    }

    private TicketDto convertToTicketDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
}
