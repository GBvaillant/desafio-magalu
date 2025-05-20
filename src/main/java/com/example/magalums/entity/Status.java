package com.example.magalums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status")
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

    public Long getStatus_id() {
        return status_id;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
