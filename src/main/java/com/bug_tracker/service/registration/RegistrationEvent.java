package com.bug_tracker.service.registration;

import com.bug_tracker.model.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationEvent extends ApplicationEvent {

    private User user;
    private String appUrl;

    public RegistrationEvent(User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    public User getUser(){
        return user;
    }
}
