package com.example.backend.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
public class StockImport {
    @Id
    private String importId;
    private Timestamp importDate;
    private long totalCost;
    private String status;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    @JsonBackReference  // Sử dụng @JsonBackReference ở đây để ngăn lặp vô tận khi Employee có stockImport
    private Employees employee;

    @OneToMany(mappedBy = "stockImport", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "import-stockDetail")  // Dùng @JsonManagedReference ở đây để chỉ định mặt trước của mối quan hệ
    private List<StockImportDetails> stockImportDetail;
}
