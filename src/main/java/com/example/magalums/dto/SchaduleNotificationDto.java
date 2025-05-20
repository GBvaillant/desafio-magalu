package com.example.magalums.dto;

import com.example.magalums.entity.Channel;
import com.example.magalums.entity.Notification;
import com.example.magalums.entity.Status;

import java.time.LocalDateTime;

public record SchaduleNotificationDto(LocalDateTime dateTime,
                                      String message,
                                      String destination,
                                      Channel.Values channel) {

  public Notification toNotification() {
    return new Notification(
            dateTime,
            message,
            destination,
            channel.toChannel(),
            Status.Values.PENDING.toStatus());
  }
}
