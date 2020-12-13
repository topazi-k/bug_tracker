package com.bug_tracker.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bug_tracker.model.Project;
import com.bug_tracker.service.ProjectService;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectService projectService;
    
    private static Project createProject() {
        Project project=new Project();
        return project;
    }
    
    private String createProjectJson() {
        String projectJson="";
        return projectJson;
    }
    

    
    

}
