package com.bug_tracker.service.registration;

import com.bug_tracker.model.User;
import com.bug_tracker.model.registration.VerificationToken;
import com.bug_tracker.service.EmailServiceImpl;
import com.bug_tracker.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.bug_tracker.security.SecurityConstants.TOKEN_PARAM;
import static com.bug_tracker.security.SecurityConstants.VERIFIC_MAIL_SUBJECT;

@Component
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private JwtTokenService tokenService;
    private RegistrationService registrationService;
    private EmailServiceImpl emailService;

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
        registrationService.saveVerificationToken(token);
        String recipientAddress = user.getEmail();
        String subject = VERIFIC_MAIL_SUBJECT;
        String confirmUrl = event.getConfirmUrl() + "?" + TOKEN_PARAM + "=" + jwtToken;
        emailService.sendConfirmMail(recipientAddress, subject, confirmUrl);
        System.out.println("message sended");
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
    public void setEmailService(EmailServiceImpl emailService){
        this.emailService = emailService;
   }
}
