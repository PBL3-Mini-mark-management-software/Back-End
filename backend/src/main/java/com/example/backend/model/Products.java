package com.example.backend.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
public class Products {
    @Id
    private String productId;

    private String name;
    private Integer quantity;
    private Integer price;
    private Date expirationDate;
    private Integer sell;
    private String img = "default_image.png";
    private String status;
    
@ManyToOne
@JoinColumn(name = "categoryId")
@JsonIgnoreProperties("products") // bỏ qua danh sách products trong Category khi serialize
private Category category;

@ManyToOne
@JoinColumn(name = "supplierId")
@JsonIgnoreProperties("products") // bỏ qua danh sách products trong Suppliers khi serialize
private Suppliers supplier;

@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonManagedReference(value = "product-order")
private List<Order_details> order_detail;

@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonManagedReference(value = "product-stockDetail")
private List<StockImportDetails> stockImportDetail;


}
