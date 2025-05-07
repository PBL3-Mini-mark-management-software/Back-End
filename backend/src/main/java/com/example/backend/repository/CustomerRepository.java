package com.example.backend.repository;
import com.example.backend.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, String> {
}
