package com.example.docInsights.repository;

import com.example.docInsights.model.Extraction;
import com.example.docInsights.model.Fields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FieldsRepo extends JpaRepository<Fields, Integer> {
    List<Fields> findByExtraction(Extraction extraction);
}
