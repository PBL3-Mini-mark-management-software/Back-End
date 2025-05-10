package com.example.backend.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StockImportRequest {
    private String importId;
    private String employeeId;
    private List<StockImportDetailRequest> details;
}
