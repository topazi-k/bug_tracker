package com.bug_tracker.service.mapper_dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserDto;

public class UserListToUserDtoConverter implements Converter<List<User> ,List<UserDto>>{

    @Override
    public List<UserDto> convert(MappingContext<List<User>, List<UserDto>> context) {
        List<User> users=context.getSource();
        List<UserDto> usersDto=new ArrayList<>();
        for(User user: users) {
            UserDto userDto=new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail("emailTestconverter"/*user.getEmail()*/);

            userDto.setCreatedAt(user.getCreatedAt());
            usersDto.add(userDto);
        }
        return usersDto;
    }
    
}
