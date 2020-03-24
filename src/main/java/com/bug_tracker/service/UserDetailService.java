package com.bug_tracker.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bug_tracker.model.User;
import com.bug_tracker.model.UserDetailsSecurity;
import com.bug_tracker.repository.spring_data.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user;
        try {
            user = userRepo.findByEmail(userEmail);
            System.out.print(user.getRole().getRole());
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("user with email " + userEmail + " not found");
        }
        return new UserDetailsSecurity(user);
    }

}
