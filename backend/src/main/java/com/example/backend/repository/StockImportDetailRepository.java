package com.example.backend.repository;
import com.example.backend.model.StockImportDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockImportDetailRepository extends JpaRepository<StockImportDetails, String> {
}
