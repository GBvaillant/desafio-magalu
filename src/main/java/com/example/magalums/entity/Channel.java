package com.example.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "channel")
@Getter
@Setter
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
}
