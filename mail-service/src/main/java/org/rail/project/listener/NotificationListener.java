package org.rail.project.listener;

import lombok.RequiredArgsConstructor;
import org.rail.project.event.OrderPlacedEvent;
import org.rail.project.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlaceEvent) {
        System.out.println(orderPlaceEvent);
        emailService.sendEmail("rail.shabayev@gmail.com", "order placed", orderPlaceEvent.toString());
    }
}
