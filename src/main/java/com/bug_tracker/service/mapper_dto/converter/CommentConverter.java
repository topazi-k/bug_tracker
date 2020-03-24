package com.bug_tracker.service.mapper_dto.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.bug_tracker.model.TicketComment;
import com.bug_tracker.model.dto.TicketCommentDto;
import com.bug_tracker.model.dto.UserDto;

public class CommentConverter implements Converter<TicketComment, TicketCommentDto>{

    @Override
    public TicketCommentDto convert(MappingContext<TicketComment, TicketCommentDto> context) {
        TicketComment comment=context.getSource();
        TicketCommentDto commentDto=new TicketCommentDto();
        commentDto.setId(comment.getId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setComment(comment.getComment());
        commentDto.setCreatedBy(new UserDto());
        
        
        return null;
    }

}
