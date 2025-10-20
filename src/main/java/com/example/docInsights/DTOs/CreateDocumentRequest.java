package com.example.docInsights.DTOs;

public record CreateDocumentRequest(
        @jakarta.validation.constraints.NotBlank String documentHash,
        String fileName,
        String mime,
        Integer pages
) {}