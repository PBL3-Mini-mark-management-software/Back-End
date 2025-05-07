package com.example.backend.service;

import com.example.backend.model.Products;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(String id){
        return productRepository.findById(id);
    }

    public List<Products> searchByName(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Products saveProducts(Products product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            // Log lỗi chi tiết nếu có
            System.err.println("Lỗi khi lưu sản phẩm: " + e.getMessage());
            throw new RuntimeException("Không thể lưu sản phẩm. Chi tiết: " + e.getMessage());
        }
    }
       

    public void deleteProduct(String productId) {
    // Kiểm tra sản phẩm có tồn tại hay không
    Optional<Products> product = productRepository.findById(productId);
    if (product.isPresent()) {
        productRepository.deleteById(productId); // Xóa sản phẩm
    } else {
        // Thay vì ném ResourceNotFoundException, sử dụng NoSuchElementException
        throw new NoSuchElementException("Sản phẩm không tồn tại");
    }
}
    public List<Products> getProductByCategory(String categoryId){
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    public List<Products> getProductBySupplier(String supplierId){
        return productRepository.findBySupplier_SupplierId(supplierId);
    }
}
