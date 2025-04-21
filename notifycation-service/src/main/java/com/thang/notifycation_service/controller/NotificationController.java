package com.thang.notifycation_service.controller;

import com.thang.notifycation_service.dto.request.Recipient;
import com.thang.notifycation_service.dto.request.SendEmailRequest;
import com.thang.event.NotificationEvent;
import com.thang.notifycation_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @KafkaListener(topics = "notification-delivery2")
    public void listenNotificationDelivery(NotificationEvent message){
        log.info("Message received: {}", message);
        emailService.sendEmail(SendEmailRequest.builder()
                .to(Recipient.builder()
                        .email(message.getRecipient())
                        .build())
                .subject(message.getSubject())
                .htmlContent(message.getBody())
                .build());
    }
}