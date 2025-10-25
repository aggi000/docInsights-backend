package com.example.docInsights.DTOs;

public record DocumentWithSummaryResponse(
        int documentId,
        String documentHash,
        SummaryDTO summary
) {}
