package com.bug_tracker.repository.spring_data;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByProjectMembers_Id(long userId);
}
