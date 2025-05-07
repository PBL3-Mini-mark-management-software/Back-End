package com.example.backend.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class StockImport {
    @Id
    private String importId;
    private Timestamp importDate;
    private long totalCost;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employees employee;

    @OneToMany(mappedBy = "stockImport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockImportDetails> stockImportDetail;
}
