package com.thang.notifycation_service.service;

import com.thang.notifycation_service.dto.request.EmailRequest;
import com.thang.notifycation_service.dto.request.SendEmailRequest;
import com.thang.notifycation_service.dto.request.Sender;
import com.thang.notifycation_service.dto.response.EmailResponse;
import com.thang.notifycation_service.exception.AppException;
import com.thang.notifycation_service.exception.ErrorCode;
import com.thang.notifycation_service.repository.httpClient.EmailClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    @Value("${notification.email.brevo-apikey}")
    private String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Devteria DotCom")
                        .email("devteriadotcom@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {

            log.info(emailRequest.toString());
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e){
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
