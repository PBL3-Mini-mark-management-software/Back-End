package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StockImportDetails {
    @Id
    private String importDetailId;

    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;

@ManyToOne
@JoinColumn(name = "productId")
@JsonIgnoreProperties({"stockImportDetail"}) // Chỉ ignore phần stockImportDetail
private Products product;

@ManyToOne
@JoinColumn(name = "importId")
@JsonBackReference(value = "import-stockDetail") // NGĂN vòng lặp StockImport -> StockImportDetail -> StockImport
private StockImport stockImport;
}
