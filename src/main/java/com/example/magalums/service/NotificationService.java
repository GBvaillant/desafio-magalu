package com.example.magalums.service;

import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.entity.Notification;
import com.example.magalums.entity.Status;
import com.example.magalums.repository.NotificationRepository;
import com.example.magalums.scheduler.MagaluTaskScheduler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SqsClient sqsClient;
    private final String queueUrl;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class);

    public NotificationService(NotificationRepository notificationRepository,
                               SqsClient sqsClient,
                               ObjectMapper objectMapper,
                               @Value("${aws.sqs.queue.url}") String queueUrl) {
        this.notificationRepository = notificationRepository;
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
        this.queueUrl = queueUrl;
    }

    public void scheduleNotification(SchaduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public void cancelNotification(Long id) {
        var notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        } else {
            throw new RuntimeException("Notification not found");
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications =  notificationRepository.findByStatusInAndDateTimeBefore(List.of(
                Status.Values.PENDING.toStatus(),
                Status.Values.ERROR.toStatus()),
         dateTime);

        notifications.forEach(getNotificationConsumer());
    }

    public void sendNotification(Notification notification) {
      try {
          String message = objectMapper.writeValueAsString(notification);

          SendMessageRequest request = SendMessageRequest.builder()
                  .queueUrl(queueUrl)
                  .messageBody(message)
                  .build();
          sqsClient.sendMessage(request);
      } catch (Exception e) {
          notification.setStatus(Status.Values.ERROR.toStatus());
          notificationRepository.save(notification);
          throw new RuntimeException("Failed to send notification", e);
      }
    }

    private Consumer<Notification> getNotificationConsumer() {
        return n -> {
                try {
                    sendNotification(n);
                    n.setStatus(Status.Values.SUCCESS.toStatus());
                    logger.info("Notification sent SUCCESS: {}", n);
                } catch (Exception e) {
                    n.setStatus(Status.Values.ERROR.toStatus());
                    logger.info("Error sending notification: {}\n error: {}", n, e.getMessage());

                }

            notificationRepository.save(n);
        };
    }
}
