package com.bug_tracker.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bug_tracker.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
