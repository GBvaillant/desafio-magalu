package com.example.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chanel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    @Id
    private Long channel_id;

    private String description;

    @AllArgsConstructor
    @NoArgsConstructor
    public enum Values {
        EMAIL(1L, "Email"),
        SMS(2L, "SMS"),
        PUSH_NOTIFICATION(3L, "Push_Notification");

        private Long id;
        private String description;

        public Channel toChannel() {
            return new Channel(id, description);
        }
    }
}
