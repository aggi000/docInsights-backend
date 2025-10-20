package com.example.docInsights.repository;

import com.example.docInsights.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DocumentRepo extends JpaRepository<Document, Integer> {
    Optional<Document> findByDocumentHash(String documentHash);
}

