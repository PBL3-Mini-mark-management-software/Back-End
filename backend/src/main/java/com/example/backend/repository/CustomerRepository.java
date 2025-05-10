package com.example.backend.repository;
import com.example.backend.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customers, String> {
    boolean existsByPhone(String phone);
    @Query("SELECT MAX(c.ind) FROM Customers c")
    Long findMaxInd();
}
