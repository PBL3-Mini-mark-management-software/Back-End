package com.example.backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockImportDetailRequest {
    private String importDetailId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private String productId;
    private String productName;
}
