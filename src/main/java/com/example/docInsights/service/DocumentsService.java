package com.example.docInsights.service;

import com.example.docInsights.DTOs.*;
import com.example.docInsights.NotFoundException;
import com.example.docInsights.mappers.DocumentMapper;
import com.example.docInsights.mappers.ExtractionMapper;
import com.example.docInsights.mappers.FieldMapper;
import com.example.docInsights.repository.DocumentRepo;
import com.example.docInsights.repository.ExtractRepo;
import com.example.docInsights.repository.FieldsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentsService {
    private final DocumentRepo documentRepo;
    private final ExtractRepo extractionRepo;
    private final FieldsRepo fieldsRepo;

    private final DocumentMapper documentMapper;
    private final ExtractionMapper extractionMapper;
    private final FieldMapper fieldMapper;

    public CreateDocumentResponse findOrCreate(CreateDocumentRequest req) {
        var existing = documentRepo.findByDocumentHash(req.documentHash());
        if (existing.isPresent())
            return documentMapper.toResponse(existing.get());
        var entity = documentMapper.toEntity(req);
        var save =  documentRepo.save(entity);
        return documentMapper.toResponse(save);
    }

    public CreateExtractionResponse createOrGet(int documentId,CreateExtractionRequest req) {
        var doc = documentRepo.findById(documentId)
                .orElseThrow(() -> new NotFoundException("document not found"));
        var existing = extractionRepo.findByDocumentAndExtractionVersion(doc,req.extractionVersion());
        if(existing.isPresent()){
            var e = existing.get();
            return new CreateExtractionResponse(e.getExtractionId(),e.getExtractionVersion(),e.getCreatedAt());
        }
        var extraction = extractionMapper.toExtraction(req,doc);
        extractionRepo.save(extraction);
        var fields = extractionMapper.toFields(req.fields(), extraction);
        fieldsRepo.saveAll(fields);

        return new CreateExtractionResponse(extraction.getExtractionId(),extraction.getExtractionVersion(),extraction.getCreatedAt());
    }

    public LatestExtractionResponse getLatestExtraction(int documentId) {
        var doc = documentRepo.findById(documentId).orElseThrow(() -> new NotFoundException("document not found"));
        var extraction = extractionRepo.findTopByDocumentOrderByCreatedAtDesc(doc).orElseThrow(() -> new NotFoundException("document not found"));
        var fieldDtos = fieldsRepo.findByExtraction(extraction).stream()
                .map(f->new FieldDTO(
                        f.getName(),f.getValue(),f.getUnits(),f.getConfidence(),f.getPage(),f.getMethod()
                )).toList();

        return new LatestExtractionResponse(documentId,extraction.getExtractionVersion(),extraction.getModelName(), extraction.getRuntimeMs(), extraction.getCreatedAt(), fieldDtos);
    }
}
