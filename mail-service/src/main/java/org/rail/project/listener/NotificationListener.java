package org.rail.project.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rail.project.event.OrderPlacedEvent;
import org.rail.project.event.ProductMadeEvent;
import org.rail.project.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlaceEvent) {
        System.out.println(orderPlaceEvent);
        emailService.sendEmail("rail.shabayev@gmail.com", "order placed", orderPlaceEvent.toString());
    }

    @KafkaListener(topics = "2notificationTopic")
    public void handle2Notification(ProductMadeEvent productMadeEvent) {
        log.info("new product is made " + productMadeEvent);
        emailService.sendEmail("rail.shabayev@gmail.com", "product made", productMadeEvent.toString());
    }
}
