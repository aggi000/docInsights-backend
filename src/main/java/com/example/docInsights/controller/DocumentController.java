package com.example.docInsights.controller;

import com.example.docInsights.DTOs.*;
import com.example.docInsights.service.DocumentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DocumentController {
    @Autowired
    private DocumentsService service;
    @GetMapping("/")
    public String check(){
        return "the backend is working";
    }

    @PostMapping("/documents")
    public ResponseEntity<CreateDocumentResponse> addDoc(@Valid @RequestBody CreateDocumentRequest req){
        var res = service.findOrCreate(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @PostMapping("/{documentId}/extractions")
    public ResponseEntity<CreateExtractionResponse> addExtraction(@PathVariable int documentId, @Valid @RequestBody CreateExtractionRequest req){
        var res = service.createOrGet(documentId,req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @GetMapping("/{documentId}/extractions/latest")
    public LatestExtractionResponse getLatestExtractions(@PathVariable int documentId){
        return service.getLatestExtraction(documentId);

    }
}
