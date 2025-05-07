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
