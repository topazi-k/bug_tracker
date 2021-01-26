package com.bug_tracker.service.registration;

import com.bug_tracker.exceptions.custom.UserAlreadyExistsException;
import com.bug_tracker.model.User;
import com.bug_tracker.model.UserRole;
import com.bug_tracker.model.registration.VerificationToken;
import com.bug_tracker.repository.spring_data.UserRepository;
import com.bug_tracker.repository.spring_data.VerificationTokenRepository;
import com.bug_tracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    private UserRepository userRepo;
    private VerificationTokenRepository verificationTokenRepository;
    private RoleService roleService;

    public User createUser(User user) {
        UserRole role = roleService.getRoleById(4);
        user.setRole(role);
        user.setEnabled(false);

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        return userRepo.save(user);
    }

    public void confirmEmail(String verificationToken){
        VerificationToken token = verificationTokenRepository.findById(verificationToken).get();
        User user = token.getUser();
        user.setEnabled(true);
        verificationTokenRepository.delete(token);
    }

    public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
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

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
