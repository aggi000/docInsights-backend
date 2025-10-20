package com.example.docInsights.DTOs;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public record LatestExtractionResponse(
        int documentId,
        String extractionVersion,
        String modelName,
        Integer runtimeMs,
        Timestamp createdAt,
        List<FieldDTO> fields
) {}
