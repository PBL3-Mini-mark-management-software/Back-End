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
//Linh
package com.example.demo.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Products {
    @Id
    private String product_id;

    private String name;
    private String img = "default_image.png";
    private Integer quantity;
    private Integer price;
    private Timestamp expiration_date;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier;

    @OneToMany(mappedBy = "product")
    private List<Order_details> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("products") 
    private List<StockImportDetails> stockImportDetail;

    // Getter and Setter for product_id
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for img
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // Getter and Setter for quantity
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for price
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // Getter and Setter for expiration_date
    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    // Getter and Setter for category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Getter and Setter for supplier
    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    // Getter and Setter for order_detail
    public List<Order_details> getOrder_detail() {
        return orderDetails;
    }

    public void setOrder_detail(List<Order_details> order_detail) {
        this.orderDetails = order_detail;
    }

    // Getter and Setter for stockImportDetail
    public List<StockImportDetails> getStockImportDetail() {
        return stockImportDetail;
    }

    public void setStockImportDetail(List<StockImportDetails> stockImportDetail) {
        this.stockImportDetail = stockImportDetail;
    }
}
