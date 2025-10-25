package com.example.docInsights.DTOs;

import java.util.Comparator;
import java.util.List;

public record SummaryDTO(
        String vendor,
        String invoiceNumber,
        String invoiceDate, // ISO yyyy-MM-dd (string is fine for now)
        String currency,
        String total,
        String subtotal,
        String tax,
        String poNumber
) {
    public static FieldDTO pickBest(List<FieldDTO> all, String name) {
        return all.stream()
                .filter(f -> name.equalsIgnoreCase(f.name()))
                .max(Comparator.comparing(f -> f.confidence() == null ? 0.0 : f.confidence()))
                .orElse(null);
    }
    public SummaryDTO(List<FieldDTO> fields) {
        this(
                valueOrNull(pickBest(fields, "vendor")),
                valueOrNull(pickBest(fields, "invoiceNumber")),
                valueOrNull(pickBest(fields, "invoiceDate")),
                unitsOrNull(pickBest(fields, "total")),
                valueOrNull(pickBest(fields, "total")),
                valueOrNull(pickBest(fields, "subtotal")),
                valueOrNull(pickBest(fields, "tax")),
                valueOrNull(pickBest(fields, "poNumber"))
        );
    }

    private static String valueOrNull(FieldDTO f) {
        return f != null ? f.value() : null;
    }

    private static String unitsOrNull(FieldDTO f) {
        return f != null ? f.units() : null;
    }
}
