package com.example.magalums.controller;

import com.example.magalums.dto.NotificationResponseDto;
import com.example.magalums.dto.SchaduleNotificationDto;
import com.example.magalums.entity.Notification;
import com.example.magalums.service.NotificationService;
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

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDto> getNotification(@PathVariable("id") Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);

        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Notification found: " + notification);
        return ResponseEntity.ok(NotificationResponseDto.fromEntity(notification.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> cancelNotification(@PathVariable("id") Long id) {
      notificationService.cancelNotification(id);
      return ResponseEntity.noContent().build();
}
}