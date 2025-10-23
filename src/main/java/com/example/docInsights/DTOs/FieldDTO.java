package com.example.docInsights.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record FieldDTO(
        @NotBlank String name,
        String value,
        String units,
        Date invoiceDate,
        Double confidence,
        Integer page,
        String method
) {}
