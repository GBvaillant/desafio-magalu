package com.example.magalums.service;

import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(SchaduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }
}
