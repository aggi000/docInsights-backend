package com.example.docInsights.repository;

import com.example.docInsights.model.DocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<DocumentInfo, String> {

}
