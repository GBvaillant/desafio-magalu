package com.example.magalums.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;

    private String destination;

    private String message;

    public Notification() {
    }

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Notification(LocalDateTime dateTime, String destination, String message, Channel channel, Status status) {
        this.dateTime = dateTime;
        this.destination = destination;
        this.message = message;
        this.channel = channel;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getMessage() {
        return message;
    }

    public Channel getChannel() {
        return channel;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
