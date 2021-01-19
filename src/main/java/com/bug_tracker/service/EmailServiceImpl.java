package com.bug_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    private JavaMailSender mailSender;

    public void sendConfirmMail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tolik.hazak@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }


    @Autowired
    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
}
