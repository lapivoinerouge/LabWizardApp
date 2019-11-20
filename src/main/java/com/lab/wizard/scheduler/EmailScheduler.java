package com.lab.wizard.scheduler;

import com.lab.wizard.config.AdminConfig;
import com.lab.wizard.email.Mail;
import com.lab.wizard.email.SimpleEmailService;
import com.lab.wizard.repository.UndoneResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    public static final String SUBJECT = "Badania w toku";

    @Autowired
    SimpleEmailService simpleEmailService;
    @Autowired
    UndoneResultRepository repository;
    @Autowired
    AdminConfig adminConfig;

    @Scheduled(cron = "0 0 18 * * *")
    public void sendInformationEmail() {
        long size = repository.countByDoneIsFalse();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Liczba badań czekających na wykonanie: " + size));
    }
}
