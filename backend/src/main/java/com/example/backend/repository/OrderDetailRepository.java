package com.example.backend.repository;
import com.example.backend.model.Order_details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<Order_details, String> {
}

