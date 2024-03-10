package org.rail.project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class MailConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfiguration.class);

    @Scheduled(fixedRate = 5000)
    public void currentTime() {
        LOGGER.warn("rail was here");
    }

}
