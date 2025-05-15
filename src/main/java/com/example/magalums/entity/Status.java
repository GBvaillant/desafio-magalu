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
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    private Long status_id;

    private String description;

    @AllArgsConstructor
    public enum Values {
        PENDING(1L, "Pending"),
        SUCCESS(2L, "Success"),
        ERROR(3L, "Error"),
        CANCELED(4L, "Canceled");

        private Long id;
        private String description;

        public Status toStatus() {
            return new Status(id, description);
        }
    }
}
