package com.example.backend.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
public class Products {
    @Id
    private String productId;

    private String name;
    private Integer quantity;
    private Integer price;
    private Timestamp expirationDate;
    private Integer sell;
    private String img = "default_image.png";
    
@ManyToOne
@JoinColumn(name = "categoryId")
@JsonIgnoreProperties("products") // bỏ qua danh sách products trong Category khi serialize
private Category category;

@ManyToOne
@JoinColumn(name = "supplierId")
@JsonIgnoreProperties("products") // bỏ qua danh sách products trong Suppliers khi serialize
private Suppliers supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order_details> order_detail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockImportDetails> stockImportDetail;
}
