package com.bug_tracker.repository.spring_data;

import com.bug_tracker.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRoleName(String roleName);
}
