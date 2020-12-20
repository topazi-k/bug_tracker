package com.bug_tracker.service;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.User;
import com.bug_tracker.repository.spring_data.ProjectRepository;
import com.bug_tracker.repository.spring_data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectService {

    private ProjectRepository projectRepo;
    private UserRepository userRepo;
    private UserService userService;

    public ProjectService(ProjectRepository projectRepo, UserService userService, UserRepository userRepository) {
        this.projectRepo = projectRepo;
        this.userService = userService;
        this.userRepo = userRepository;
    }

    public Project create(Project project) {
        return projectRepo.save(project);
    }

    public Project findById(long id) {
        try {
            return projectRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + " not found");
        }
    }

    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    public List<Project> findAllByUser(long userId) {
        return projectRepo.findProjectsByProjectMembers_Id(userId);
    }

    public Project update(Project project) {
        return projectRepo.save(project);
    }

    public void delete(long id) {
        projectRepo.deleteById(id);
    }

    public Project addUser(long projectId, long userId) {
        User user = userService.findById(userId);
        Project project = findById(projectId);
        project.addUser(user);
        return update(project);
    }

    public Project removeUser(long projectId, long userId) {
        User user = userService.findById(userId);
        Project project = findById(projectId);
        project.removeUser(user);
        return update(project);
    }
}
