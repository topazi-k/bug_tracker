package com.bug_tracker.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.User;
import com.bug_tracker.repository.spring_data.ProjectRepository;

@Service
public class ProjectService {

    private ProjectRepository projectRepo;
    private UserService userService;

    public ProjectService(ProjectRepository projectRepo, UserService userRepo) {
        this.projectRepo = projectRepo;
        this.userService= userRepo;
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

    public Project update(Project project) {
        return projectRepo.save(project);
    }

    public void delete(long id) {
        projectRepo.deleteById(id);
    }

    public Project addUser(long projectId, long userId) {
        User user= userService.findById(userId);
        Project project=findById(projectId);
        project.addUser(user);
        return update(project);
    }
    
    public Project removeUser(long projectId,long userId) {
        User user=userService.findById(userId);
        Project project=findById(projectId);
        project.removeUser(user);
        return update(project);
    }
}
