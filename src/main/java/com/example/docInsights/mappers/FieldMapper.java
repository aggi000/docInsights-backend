package com.example.docInsights.mappers;

import com.example.docInsights.DTOs.FieldDTO;
import com.example.docInsights.model.Extraction;
import com.example.docInsights.model.Fields;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FieldMapper {

    @Mapping(target = "fieldId",   ignore = true)
    @Mapping(target = "extraction", ignore = true)   // set in @AfterMapping
    @Mapping(target = "createdAt", ignore = true)
    Fields toEntity(FieldDTO dto, @Context Extraction extraction);

    List<Fields> toEntities(List<FieldDTO> dtos, @Context Extraction extraction);

    @AfterMapping
    default void setParent(@MappingTarget Fields field, @Context Extraction extraction) {
        field.setExtraction(extraction);
    }
}


