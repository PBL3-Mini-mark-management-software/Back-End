package com.example.backend.service;

import com.example.backend.DTO.StockImportRequest;
import com.example.backend.DTO.StockImportDetailRequest;
import com.example.backend.model.*;
import com.example.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockImportDetailService {

    @Autowired
    private StockImportRepository stockImportRepository;

    @Autowired
    private StockImportDetailsRepository stockImportDetailsRepository;

    @Autowired
    private EmployeeRepository employeesRepository;

    @Autowired
    private ProductRepository productsRepository;

    public StockImport createStockImport(StockImportRequest request) {
        StockImport stockImport = new StockImport();
        stockImport.setImportId(request.getImportId());
        stockImport.setImportDate(Timestamp.from(Instant.now()));

        Employees employee = employeesRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        stockImport.setEmployee(employee);

        List<StockImportDetails> detailsList = new ArrayList<>();
        long totalCost = 0;

        for (StockImportDetailRequest detailReq : request.getDetails()) {
            Products product = productsRepository.findById(detailReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            StockImportDetails detail = new StockImportDetails();
            detail.setImportDetailId(detailReq.getImportDetailId());
            detail.setProduct(product);
            detail.setQuantity(detailReq.getQuantity());
            detail.setUnitPrice(detailReq.getUnitPrice());
            detail.setTotalPrice(detailReq.getQuantity() * detailReq.getUnitPrice());
            detail.setStockImport(stockImport);

            // Cập nhật tồn kho
            product.setQuantity(product.getQuantity() + detailReq.getQuantity());

            detailsList.add(detail);
            totalCost += detail.getTotalPrice();
        }

        stockImport.setTotalCost(totalCost);
        stockImport.setStockImportDetail(detailsList);

        return stockImportRepository.save(stockImport); // cascade sẽ tự lưu details
    }
}
