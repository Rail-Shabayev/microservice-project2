package org.rail.project;

import org.rail.project.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MailApplication {

    private final EmailService emailService;

    public MailApplication(EmailService emailService) {
        this.emailService = emailService;
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MailApplication.class);
        MailApplication mailApplication = context.getBean(MailApplication.class);
        mailApplication.Run();
    }

    private void Run(){
        emailService.sendEmail("rail.shabayev@gmail.com", "very important", "this is my mail from intelj idea");
    }

}