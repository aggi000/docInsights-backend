package com.example.docInsights.DTOs;


import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateExtractionRequest(
        @NotBlank String extractionVersion,
        String modelName,
        Integer runtimeMs,
        @NotEmpty @Valid List<FieldDTO> fields   // @Valid to cascade validation into FieldDTO
) {}
