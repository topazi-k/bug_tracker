package com.bug_tracker.model.registration;

import com.bug_tracker.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.bug_tracker.security.SecurityConstants.VERIFICATION_DURATION;

@Entity
@Data
public class VerificationToken {

    public VerificationToken() {
        expiryDate = LocalDateTime.now().plusHours(VERIFICATION_DURATION);
    }

    @Id
    @Column(name = "token")
    private String jwtToken;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

}
