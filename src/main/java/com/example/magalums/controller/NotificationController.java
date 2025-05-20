package com.example.magalums.controller;

import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.entity.Notification;
import com.example.magalums.service.NotificationService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> scheduleNotification(@RequestBody SchaduleNotificationDto dto) {
        notificationService.scheduleNotification(dto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(@PathVariable("notificationId") Long notificationId) {
        Optional<Notification> notification = notificationService.getNotificationById(notificationId);

        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notification.get());
    }
}
