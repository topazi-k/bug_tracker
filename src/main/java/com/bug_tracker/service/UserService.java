package com.bug_tracker.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bug_tracker.model.User;
import com.bug_tracker.repository.spring_data.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User create(User user) {
        return userRepo.save(user);
    }

    public User findById(long id) {
        try {
            return userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + " not found");
        }
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public User update(User user) {
        User userToUpdate = findById(user.getId());
                                                    // how to update?????????????????
        return userRepo.save(userToUpdate);
    }

    public void delete(long id) {
        userRepo.deleteById(id);
    }

}
