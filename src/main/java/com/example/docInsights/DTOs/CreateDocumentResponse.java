package com.example.docInsights.DTOs;

import java.sql.Timestamp;
import java.time.Instant;

public record CreateDocumentResponse(
        Long documentId,
        String documentHash,
        Timestamp createdAt
) {}
