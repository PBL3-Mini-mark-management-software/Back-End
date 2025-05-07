package com.example.backend.repository;
import com.example.backend.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products, String> {
    List<Products> findByNameContainingIgnoreCase(String name);
    List<Products> findByCategory_CategoryId(String categoryId);
    List<Products> findBySupplier_SupplierId(String supplierId);
}

