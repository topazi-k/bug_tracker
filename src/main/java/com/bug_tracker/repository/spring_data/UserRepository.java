package com.bug_tracker.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bug_tracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    public User findByEmail(String email);
}
