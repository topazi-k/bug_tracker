package com.bug_tracker.service.registration;

import com.bug_tracker.model.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationEvent extends ApplicationEvent {

    private User user;
    private String confirmUrl;

    public RegistrationEvent(User user, String confirmUrl) {
        super(user);
        this.user = user;
        this.confirmUrl =confirmUrl;
    }

    public User getUser() {
        return user;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }
}
