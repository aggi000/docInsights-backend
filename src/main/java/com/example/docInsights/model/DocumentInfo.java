package com.example.docInsights.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfo {
    @Id
    private String documentId;
    private BigDecimal total;
    private Date invoice_date;
    private String vendor;
    private String po_number;
}
