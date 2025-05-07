package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Customers {
    @Id
    private String customerId;
    private String name;
    private String phone; // sửa thành String
    private Integer points;

    @OneToMany(mappedBy = "customer")
    private List<Orders> order;
}
