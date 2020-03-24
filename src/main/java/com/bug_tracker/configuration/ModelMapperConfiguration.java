package com.bug_tracker.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bug_tracker.service.mapper_dto.CommentMap;
import com.bug_tracker.service.mapper_dto.ProjectMap;
import com.bug_tracker.service.mapper_dto.TicketMap;
import com.bug_tracker.service.mapper_dto.TicketSimpleMap;

@Configuration
public class ModelMapperConfiguration {

    private ProjectMap projectMap;
    private TicketMap ticketMap;
    private CommentMap commentMap;
    private TicketSimpleMap ticketSimpleMap;
    public ModelMapperConfiguration(ProjectMap projectMap, TicketMap ticketMap, CommentMap commentMap, TicketSimpleMap ticketSimpleMap) {

        this.projectMap = projectMap;
        this.ticketMap = ticketMap;
        this.commentMap = commentMap;
        this.ticketSimpleMap= ticketSimpleMap;
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(projectMap);
        modelMapper.addMappings(ticketMap);
        modelMapper.addMappings(commentMap);
        modelMapper.addMappings(ticketSimpleMap);
        return modelMapper;
    }
}
