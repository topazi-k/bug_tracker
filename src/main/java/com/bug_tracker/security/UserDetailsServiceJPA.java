package com.bug_tracker.security;

import com.bug_tracker.model.User;
import com.bug_tracker.repository.spring_data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserDetailsServiceJPA implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetailsImpl loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(userEmail);
        if (isNull(user)) throw new UsernameNotFoundException("User not found");
        return new UserDetailsImpl(user);
    }

}
