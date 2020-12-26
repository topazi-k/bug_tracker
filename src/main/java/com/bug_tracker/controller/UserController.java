package com.bug_tracker.controller;

import com.bug_tracker.model.User;
import com.bug_tracker.model.dto.UserDto;
import com.bug_tracker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> allUsers = userService.findAll();
        List<UserDto> userDtos = allUsers
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable long id) {
        if (id != userDto.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect id");
        }
        User user = userService.update(modelMapper.map(userDto, User.class));
        return new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
