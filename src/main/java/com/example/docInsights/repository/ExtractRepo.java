package com.example.docInsights.repository;

import com.example.docInsights.model.Document;
import com.example.docInsights.model.Extraction;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExtractRepo extends JpaRepository<Extraction, Integer> {
    Optional<Extraction> findByDocumentAndExtractionVersion(Document document, String extractionVersion);

    Optional<Extraction> findTopByDocumentOrderByCreatedAtDesc(Document document);
}
