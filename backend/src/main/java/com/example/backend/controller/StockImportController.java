package com.example.backend.controller;

import com.example.backend.model.Products;
import com.example.backend.model.StockImport;
import com.example.backend.model.StockImportDetails;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.StockImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/StockImport")
public class StockImportController {

    @Autowired
    private StockImportRepository stockImportRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<StockImport> getAllStockImports() {
        return stockImportRepository.findAll();
    }

    @GetMapping("/{importId}")
    public StockImport getStockImportById(@PathVariable String importId) {
        Optional<StockImport> stockImport = stockImportRepository.findById(importId);
        return stockImport.orElse(null);
    }

     // Cập nhật trạng thái và số lượng nhập kho
    @PutMapping("/{importId}/import-stock")
    public String importStock(@PathVariable String importId) {
        // Tìm đơn nhập
        Optional<StockImport> stockImportOptional = stockImportRepository.findById(importId);
        if (stockImportOptional.isEmpty()) {
            return "Không tìm thấy đơn nhập hàng.";
        }

        StockImport stockImport = stockImportOptional.get();

        // Kiểm tra trạng thái của đơn nhập, chỉ cập nhật nếu trạng thái là DRAFT
        if (!stockImport.getStatus().equals("DRAFT")) {
            return "Đơn nhập hàng không có trạng thái DRAFT.";
        }

        // Thay đổi trạng thái của đơn nhập thành ACTIVE
        stockImport.setStatus("ACTIVE");

        // Cập nhật số lượng sản phẩm trong kho và trạng thái sản phẩm
        for (StockImportDetails detail : stockImport.getStockImportDetail()) {
            // Lấy sản phẩm từ chi tiết nhập kho
            Products product = detail.getProduct();
            if (product != null) {
                // Cập nhật số lượng trong kho của sản phẩm
                product.setQuantity(product.getQuantity() + detail.getQuantity());

                // Cập nhật trạng thái của sản phẩm nếu sản phẩm đang ở trạng thái DRAFT
                if (product.getStatus() != null && product.getStatus().equals("DRAFT")) {
                    product.setStatus("ACTIVE");
                }

                // Lưu sản phẩm sau khi cập nhật
                productRepository.save(product);  
            }
        }

        // Lưu thay đổi trạng thái của đơn nhập
        stockImportRepository.save(stockImport);

        return "Nhập kho thành công!";
    }
}


