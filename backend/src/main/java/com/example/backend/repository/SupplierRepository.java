package com.example.backend.repository;
import com.example.backend.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Suppliers, String> {
    boolean existsByPhone(String phone);
    @Query("SELECT MAX(s.ind) FROM Suppliers s")
    Long findMaxInd();
}
