package com.bug_tracker.repository.spring_data;

import com.bug_tracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "Select * from tickets where project_id =?", nativeQuery = true)
    List<Ticket> findTicketsByProject_projectId(int projectId);
}
