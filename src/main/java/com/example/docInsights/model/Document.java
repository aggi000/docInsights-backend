package com.example.docInsights.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
@Table(
        name  = "documents",
        uniqueConstraints = @UniqueConstraint(columnNames = "document_hash")
)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    @Column(nullable = false,unique = true)
    private String documentHash;
    private String fileName;
    private String mime;
    private int pages;
    private Timestamp createdAt;
}
