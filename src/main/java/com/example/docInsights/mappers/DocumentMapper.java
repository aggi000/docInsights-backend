package com.example.docInsights.mappers;

import com.example.docInsights.DTOs.CreateDocumentRequest;
import com.example.docInsights.DTOs.CreateDocumentResponse;
import com.example.docInsights.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface DocumentMapper {
    @Mapping(target = "documentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Document toEntity(CreateDocumentRequest req);

    CreateDocumentResponse toResponse(Document document);
}
