package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StockImportDetails {
    @Id
    private String importDetailId; // vẫn giữ String

    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "importId")
    private StockImport stockImport;
}
