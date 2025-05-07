package com.example.backend.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    private String orderId;

    private Timestamp orderDate;
    private Integer totalAmount;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    @OneToMany(mappedBy = "order")
    private List<Order_details> order_detail;
}
