package com.bug_tracker.repository.spring_data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bug_tracker.model.TicketComment;

@Repository
public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {
    
    @Query(value="SELECT t FROM TicketComment t WHERE ticket_id=?1")
    public List<TicketComment> findByTicket(long id);
    
   
}
