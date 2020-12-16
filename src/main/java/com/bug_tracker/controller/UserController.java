package com.bug_tracker.controller;

import com.bug_tracker.model.User;
import com.bug_tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> allUsers = userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable long id) {
        if (id != user.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "incorrect id");
        }
        user = userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
