package com.example.backend.controller;

import com.example.backend.model.Products;
import com.example.backend.DTO.ProductRequestDTO;
import com.example.backend.model.Category;
import com.example.backend.model.Suppliers;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.SupplierRepository;
import com.example.backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/ProductCatalog")
@CrossOrigin(origins = "http://localhost:8081")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
public List<Products> getAllProducts(){
    List<Products> products = productsService.getAllProducts();
    System.out.println("Số lượng sản phẩm trả về: " + products.size());
    return products;
}


    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable String id){
        return productsService.getProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Products> searchByName(@RequestParam String name){
        return productsService.searchByName(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<Products> getByCategory(@PathVariable String categoryId){
        return productsService.getProductByCategory(categoryId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<Products> getBySupplier(@PathVariable String supplierId){
        return productsService.getProductBySupplier(supplierId);
    }
    @GetMapping("/categories")
public ResponseEntity<List<Category>> getCategories() {
    List<Category> categories = categoryRepository.findAll();
    return ResponseEntity.ok(categories);
}

@GetMapping("/suppliers")
public ResponseEntity<List<Suppliers>> getSuppliers() {
    List<Suppliers> suppliers = supplierRepository.findAll();
    return ResponseEntity.ok(suppliers);
}

    @PostMapping
public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO dto){
    try {
        // Kiểm tra và xử lý dữ liệu từ DTO
        Products product = new Products();
        product.setProductId(dto.getProduct_id());
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        
        // Gán giá trị mặc định cho img nếu không có giá trị
        if (dto.getImg() == null || dto.getImg().isEmpty()) {
            product.setImg("default_image.png");
        } else {
            product.setImg(dto.getImg());
        }

        // Gán giá trị mặc định cho sell nếu không có giá trị
        if (dto.getSell() == null) {
            product.setSell(0); // Gán giá trị mặc định
        } else {
            product.setSell(dto.getSell());
        }
        
        // Kiểm tra và xử lý Category và Supplier
        Category category = categoryRepository.findById(dto.getCategory_id())
            .orElseThrow(() -> new RuntimeException("❌ Category not found"));
        Suppliers supplier = supplierRepository.findById(dto.getSupplier_id())
            .orElseThrow(() -> new RuntimeException("❌ Supplier not found"));

        product.setCategory(category);
        product.setSupplier(supplier);

        // Lưu sản phẩm
        product = productsService.saveProducts(product);
        return ResponseEntity.ok(product);
    } catch (Exception ex) {
        // Log lỗi chi tiết
        System.err.println("Error: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(500).body("Lỗi server: " + ex.getMessage());
    }
}
@PostMapping("/importProduct")
public ResponseEntity<?> importProduct(@RequestBody ProductRequestDTO dto){
    try {
        // Kiểm tra và xử lý dữ liệu từ DTO
        Products product = new Products();
        product.setProductId(dto.getProduct_id());
        product.setName(dto.getName());
        product.setQuantity(0);
        product.setPrice(dto.getPrice());

        // Gán giá trị mặc định cho img nếu không có giá trị
        if (dto.getImg() == null || dto.getImg().isEmpty()) {
            product.setImg("default_image.png");
        } else {
            product.setImg(dto.getImg());
        }

        // Gán giá trị mặc định cho sell nếu không có giá trị
        if (dto.getSell() == null) {
            product.setSell(0);
        } else {
            product.setSell(dto.getSell());
        }

        // Gán mặc định status là DRAFT khi thêm mới
        product.setStatus("DRAFT");

        // Kiểm tra và xử lý Category và Supplier
        Category category = categoryRepository.findById(dto.getCategory_id())
            .orElseThrow(() -> new RuntimeException("❌ Category not found"));
        Suppliers supplier = supplierRepository.findById(dto.getSupplier_id())
            .orElseThrow(() -> new RuntimeException("❌ Supplier not found"));

        product.setCategory(category);
        product.setSupplier(supplier);

        // Lưu sản phẩm
        product = productsService.saveProducts(product);
        return ResponseEntity.ok(product);
    } catch (Exception ex) {
        System.err.println("Error: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.status(500).body("Lỗi server: " + ex.getMessage());
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable String id, @RequestBody Products updatedProduct){
        Optional<Products> existingProduct = productsService.getProductById(id);
        if (existingProduct.isPresent()){
            updatedProduct.setProductId(id); //giu nguyen id cu
            return ResponseEntity.ok(productsService.saveProducts(updatedProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable String id){
    try {
        productsService.deleteProduct(id); // Xóa sản phẩm
        return ResponseEntity.ok().build(); // Trả về 200 OK nếu xóa thành công
    } catch (NoSuchElementException e) {
        // Xử lý khi sản phẩm không tồn tại
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Trả về mã lỗi 404 nếu không tìm thấy sản phẩm
    }
}


}
