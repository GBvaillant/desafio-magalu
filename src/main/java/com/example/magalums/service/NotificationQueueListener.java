package com.example.magalums.service;

import com.example.magalums.entity.Channel;
import com.example.magalums.entity.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class NotificationQueueListener {

    private final NotificationService notificationService;

    private final ObjectMapper objectMapper;

    public NotificationQueueListener(NotificationService notificationService,
                                     ObjectMapper objectMapper) {
        this.notificationService = notificationService;
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
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}