package com.example.magalums.service;

import com.example.magalums.entity.Channel;
import com.example.magalums.entity.Notification;
import com.example.magalums.entity.Status;
import com.example.magalums.scheduler.MagaluTaskScheduler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NotificationQueueListener {

    private final NotificationService notificationService;
    private final SmsService smsService;

    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class);

    public NotificationQueueListener(NotificationService notificationService,
                                        SmsService smsService,
                                     ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.smsService = smsService;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @SqsListener("magalums")
    public void listen(String messageContent) {
        try {
            Notification notification = objectMapper.readValue(messageContent, Notification.class);

            Channel channel = notification.getChannel();

            if (channel.getChannel_id() == 1) {

                String to = notification.getDestination();
                String content = notification.getMessage();
                String subject = "Notificação de Teste";
                notificationService.sendEmail(to, content, subject);
            }

            if (channel.getChannel_id() == 2) {
                String to = notification.getDestination();
                String content = notification.getMessage();
                smsService.sendSms(to, content);
            }

            notification.setStatus(Status.Values.SUCCESS.toStatus());
            logger.info("Notification sent SUCCESS: {}", notification.getId());

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}