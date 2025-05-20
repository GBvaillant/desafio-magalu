package com.example.magalums.service;

import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.entity.Notification;
import com.example.magalums.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(SchaduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }
}
