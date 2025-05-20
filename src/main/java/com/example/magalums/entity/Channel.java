package com.example.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    private Long channel_id;

    private String description;

    public Channel(Long channel_id, String description) {
        this.channel_id = channel_id;
        this.description = description;
    }

    public Channel() {
    }

    public enum Values {
        EMAIL(1L, "Email"),
        SMS(2L, "SMS"),
        PUSH_NOTIFICATION(3L, "Push_Notification");

        private Long id;
        private String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Channel toChannel() {
            return new Channel(id, description);
        }
    }

    public String getDescription() {
        return description;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
