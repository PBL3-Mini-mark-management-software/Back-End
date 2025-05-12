package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order_details {
    @Id
    private String orderId; // bạn muốn giữ String

    private Integer productQuantity;
    private Integer subtotal;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Products product;
}
//Linh
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order_details {
    @Id
    private String order_detail_id; // Khóa chính duy nhất cho bảng Order_details

    @Column(name = "order_id", nullable = false, insertable = false, updatable = false)
    private String order_id; // Lưu trữ order_id từ Orders

    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private String product_id; // Lưu trữ product_id từ Products

    private Integer product_quantity;
    private Integer subtotal;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Orders order; // Liên kết với Orders

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  
    @JsonBackReference
    private Products product; // Liên kết với Products

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public void setOrder_detail_id(String order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}

