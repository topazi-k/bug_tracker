package com.bug_tracker.service.mapper_dto;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bug_tracker.model.TicketComment;
import com.bug_tracker.model.dto.TicketCommentDto;

@Component
public class CommentMap extends PropertyMap<TicketComment, TicketCommentDto> {

    @Override
    protected void configure() {
       
    }

}
