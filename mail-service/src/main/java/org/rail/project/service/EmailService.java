package org.rail.project.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final List<String> events = new ArrayList<>();

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("mailtrap@demomailtrap.com");
        message.setSubject(subject);
        message.setText(body);
        events.add(message.toString());
        mailSender.send(message);
    }

    public List<String> getAllEvents() {
        return events;
    }

}
