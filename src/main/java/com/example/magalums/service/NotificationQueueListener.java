package com.example.magalums.service;

import com.example.magalums.entity.Channel;
import com.example.magalums.entity.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationQueueListener {

    private final NotificationService notificationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NotificationQueueListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @SqsListener(value = "${aws.sqs.queue.url}")
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
