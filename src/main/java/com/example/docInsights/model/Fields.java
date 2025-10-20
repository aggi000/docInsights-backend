package com.example.docInsights.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Fields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fieldId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "extractionId", nullable = false)
    private Extraction extraction;
    private String name;
    private String value;
    private String units;
    private double confidence;
    private int page;
    private String method;
    private Timestamp createdAt;
}
