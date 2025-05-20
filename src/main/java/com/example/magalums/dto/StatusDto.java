package com.example.magalums.dto;

import com.example.magalums.entity.Status;

public record StatusDto(Long id, String name) {
    public static StatusDto fromEntity(Status status) {
        return new StatusDto(
                status.getStatus_id(), // exemplo, adapte ao seu campo
                status.getDescription() // ou getName()
        );
    }
}