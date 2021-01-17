package com.bug_tracker.service.registration;

import com.bug_tracker.model.User;
import com.bug_tracker.model.registration.VerificationToken;
import com.bug_tracker.repository.spring_data.UserRepository;
import com.bug_tracker.repository.spring_data.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    private UserRepository userRepo;
    private VerificationTokenRepository verificationTokenRepository;

    public User createUser(User user) {
        user.setEnabled(false);
        return userRepo.save(user);
    }

    public VerificationToken saveToken(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }
}
