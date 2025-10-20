package com.example.docInsights.mappers;

import com.example.docInsights.DTOs.CreateExtractionRequest;
import com.example.docInsights.DTOs.FieldDTO;
import com.example.docInsights.model.Document;
import com.example.docInsights.model.Extraction;
import com.example.docInsights.model.Fields;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = FieldMapper.class)
public interface ExtractionMapper {

    @Mapping(target = "extractionId",   ignore = true)
    @Mapping(target = "createdAt",      ignore = true)
    @Mapping(target = "document",       source = "document")
    @Mapping(target = "extractionVersion", source = "req.extractionVersion")
    @Mapping(target = "modelName",         source = "req.modelName")
    @Mapping(target = "runtimeMs",         source = "req.runtimeMs")
    Extraction toExtraction(CreateExtractionRequest req, Document document);

    List<Fields> toFields(List<FieldDTO> dtos, @Context Extraction extraction);
}
