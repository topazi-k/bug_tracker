package com.bug_tracker.service.registration;

import com.bug_tracker.model.User;
import com.bug_tracker.model.registration.VerificationToken;
import com.bug_tracker.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;

import static com.bug_tracker.security.SecurityConstants.*;

public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private JwtTokenService tokenService;
    private RegistrationService registrationService;
    private MessageSource messageSource;
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        this.startConfirmationProcess(event);
    }

    private void startConfirmationProcess(RegistrationEvent event) {
        User user = event.getUser();
        String jwtToken = tokenService.createJwtToken(user);
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setJwtToken(jwtToken);
        registrationService.saveToken(token);

        String recipientAddress = user.getEmail();
        String subject = VERIFIC_MAIL_SUBJECT;
        String confirmUrl = BASE_URL + EMAIL_CONFIRM_URL + "?" + TOKEN_PARAM + "=" + jwtToken;


    }

    @Autowired
    public void setTokenService(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
