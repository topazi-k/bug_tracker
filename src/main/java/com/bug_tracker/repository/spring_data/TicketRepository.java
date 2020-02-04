package com.bug_tracker.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bug_tracker.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
