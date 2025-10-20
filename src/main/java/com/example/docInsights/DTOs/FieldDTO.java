package com.example.docInsights.DTOs;

import jakarta.validation.constraints.NotBlank;

public record FieldDTO(
        @NotBlank String name,
        String value,
        String units,
        Double confidence,
        Integer page,
        String method
) {}
