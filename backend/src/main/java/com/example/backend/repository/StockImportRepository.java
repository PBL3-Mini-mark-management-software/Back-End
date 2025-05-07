package com.example.backend.repository;
import com.example.backend.model.StockImport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockImportRepository extends JpaRepository<StockImport, String> {
}
