package com.lab.wizard.google;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Watcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    @After("execution(* com.lab.wizard.google.GoogleCalendarController.createEvent(..))")
    public void logEvent() {
        LOGGER.info("Event was created.");
    }
}
