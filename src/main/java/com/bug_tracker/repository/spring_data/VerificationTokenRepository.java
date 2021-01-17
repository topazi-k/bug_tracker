package com.bug_tracker.repository.spring_data;

import com.bug_tracker.model.registration.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
}
