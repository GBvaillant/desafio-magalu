package com.example.magalums.dto;

import com.example.magalums.entity.Notification;

import java.time.LocalDateTime;

public record NotificationResponseDto(
        Long id,
        LocalDateTime dateTime,
        String destination,
        String message,
        ChannelDto channel,
        StatusDto status
) {
    public static NotificationResponseDto fromEntity(Notification notification) {
        return new NotificationResponseDto(
                notification.getId(), // ou getNotificationId()
                notification.getDateTime(),
                notification.getDestination(),
                notification.getMessage(),
                notification.getChannel() != null ? ChannelDto.fromEntity(notification.getChannel()) : null,
                notification.getStatus() != null ? StatusDto.fromEntity(notification.getStatus()) : null
        );
    }
}