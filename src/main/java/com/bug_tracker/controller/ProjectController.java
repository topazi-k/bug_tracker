package com.bug_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bug_tracker.model.Project;
import com.bug_tracker.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projects")
@Api(value="Crud operations for projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ApiOperation(value="Create project", response = Project.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 400, message = "Bad request") })

    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = projectService.create(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Find existing project by id", response=Project.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Project not found") })
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        Project project = projectService.findById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@RequestBody Project project, @PathVariable long id) {
        if (id != project.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect id");
        }
        project = projectService.update(project);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    //TODO: to decide whether to pass objects+id or just an id
    @PutMapping("/{id}/users/{userId}")
    public ResponseEntity<Project> addUser(@PathVariable(name = "id") long projectId,
            @PathVariable(name = "userId") long userId) {
        Project project = projectService.addUser(projectId, userId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }
    
    //TODO: to decide whether to pass objects+id or just an id
    @DeleteMapping("/{id}/users/{userId}")
    public ResponseEntity<Project> removeUser(@PathVariable(name = "id") long projectId,
            @PathVariable(name = "userId") long userId){
        Project project=projectService.removeUser(projectId, userId);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }
}
