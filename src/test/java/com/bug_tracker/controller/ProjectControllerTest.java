package com.bug_tracker.controller;

import com.bug_tracker.config.TestConfig;
import com.bug_tracker.model.Project;
import com.bug_tracker.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjectController.class)
@ContextConfiguration(classes = {TestConfig.class})
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectService projectServiceMock;


    @Test
    public void getById_ShouldReturnFoundProjectDto() throws Exception {

        Project project = new Project();
        project.setId(1);
        project.setProjectName("best project");
        project.setDescription("crm system for university");

        String projectJson =
                "{\n" +
                        "  \"id\": 1,\n" +
                        "  \"projectName\": \"best project\",\n" +
                        "  \"description\": \"crm system for university\"" +
                        "}";

        Mockito.when(projectServiceMock.findById(1)).thenReturn(project);
        mockMvc.perform(get("/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(projectJson));

    }

    @Test
    public void post_ShouldRunServiceCreateMethodWithReceivedProjectObject() throws Exception {

        Project project = new Project();
        project.setId(1);
        project.setProjectName("best project");
        project.setDescription("crm system for university");
        String projectJson =
                "{\n" +
                        "  \"projectName\": \"best project\",\n" +
                        "  \"description\": \"crm system for university\"" +
                        "}";

        Mockito.when(projectServiceMock.create(any(Project.class))).thenReturn(project);


        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson))
                .andExpect(status().isCreated());
        Mockito.verify(projectServiceMock).create(projectArgumentCaptor.capture());
        Project captured = projectArgumentCaptor.getValue();
        assertEquals("best project", captured.getProjectName());
    }

    @Test
    public void delete_ShouldRunServiceDeleteWithAppropriateArgument() throws Exception {
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        mockMvc.perform(delete("/projects/1"));
        Mockito.verify(projectServiceMock).delete(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), 1);
    }


}
