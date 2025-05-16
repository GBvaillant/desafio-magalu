package com.example.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status {

    @Id
    private Long status_id;

    private String description;

    public Status(Long status_id, String description) {
        this.status_id = status_id;
        this.description = description;
    }

    public Status() {
    }

    public enum Values {
        PENDING(1L, "Pending"),
        SUCCESS(2L, "Success"),
        ERROR(3L, "Error"),
        CANCELED(4L, "Canceled");

        private Long id;
        private String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Status toStatus() {
            return new Status(id, description);
        }
    }
}
