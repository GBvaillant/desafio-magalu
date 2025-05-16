package com.example.magalums.controller;

import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> scheduleNotification(SchaduleNotificationDto dto) {

        notificationService.scheduleNotification(dto);

        return ResponseEntity.accepted().build();

    }
}
