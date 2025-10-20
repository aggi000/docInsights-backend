package com.example.docInsights.DTOs;

// CreateExtractionResponse.java
import java.sql.Timestamp;
import java.time.Instant;

public record CreateExtractionResponse(
        int extractionId,
        String extractionVersion,
        Timestamp createdAt
) {}

