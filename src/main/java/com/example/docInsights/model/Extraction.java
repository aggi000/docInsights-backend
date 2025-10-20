package com.example.docInsights.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Getter@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "extractions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"documentId", "extractionVersion"})
)
public class Extraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int extractionId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "documentId", nullable = false)
    private Document document;
    @Column(nullable = false)
    private String extractionVersion;
    private String modelName;
    private int runtimeMs;
    private Timestamp createdAt;

}
