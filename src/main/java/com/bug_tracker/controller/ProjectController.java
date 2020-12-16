package com.bug_tracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import com.bug_tracker.model.dto.ProjectDto;
import com.bug_tracker.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/projects")
@Api(value = "Crud operations for projects")
public class ProjectController {

    private ProjectService projectService;
    private ModelMapper modelMapper;

    public ProjectController(ProjectService projectService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create project")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 400, message = "Bad request") })
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectDto projectDto) {
        Project project = projectService.create(converToProject(projectDto));
        return new ResponseEntity<>(convertToProjectDto(project), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find existing project by id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Project not found") })
    public ResponseEntity<ProjectDto> getById(@PathVariable Long id) {
        Project project = projectService.findById(id);
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value= "Find all projects")
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<Project> projects = projectService.findAll();
        List<ProjectDto> projectsDto = projects.stream().map(this::convertToProjectDto).collect(Collectors.toList());
        
        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }
    
    //TODO : make updating just two fields
    @PutMapping("/{id}")
    @ApiOperation(value="Update project information: projectName or description")
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto projectDto, @PathVariable long id) {
        if (id != projectDto.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect id");
        }
        Project project = projectService.update(converToProject(projectDto));
        
        return new ResponseEntity<>(convertToProjectDto(project), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation (value="Delete all project information")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    // TODO: to decide whether to pass objects+id or just an id
    @PutMapping("/{id}/users/{userId}")
    public ResponseEntity<ProjectDto> addUser(@PathVariable(name = "id") long projectId,
            @PathVariable(name = "userId") long userId) {
        Project project = projectService.addUser(projectId, userId);
        return new ResponseEntity<>(convertToProjectDto(project), HttpStatus.OK);
    }

    // TODO: to decide whether to pass objects+id or just an id
    @DeleteMapping("/{id}/users/{userId}")
    public ResponseEntity<ProjectDto> removeUser(@PathVariable(name = "id") long projectId,
            @PathVariable(name = "userId") long userId) {
        Project project = projectService.removeUser(projectId, userId);
        return new ResponseEntity<>(convertToProjectDto(project), HttpStatus.OK);
    }

    private ProjectDto convertToProjectDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);

    }

    private Project converToProject(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }
}
