package com.bug_tracker.service.mapper_dto;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bug_tracker.model.Project;
import com.bug_tracker.model.dto.ProjectDto;

@Component
public class ProjectMap extends PropertyMap<Project, ProjectDto> {

    @Override
    protected void configure() {
        //using(new UserListToUserDtoConverter()).map(source.getProjectMembers()).setProjectMembers(null);
        //using(new TicketListToTicketDtoConverter()).map(source.getTickets()).setTickets(null);
    }
}
