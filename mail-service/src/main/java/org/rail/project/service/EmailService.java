package org.rail.project.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

//import org.springframework.mail.JavaMailSender;
@org.springframework.stereotype.Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("mailtrap@demomailtrap.com");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
