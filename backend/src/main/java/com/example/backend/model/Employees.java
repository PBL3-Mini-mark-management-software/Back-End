package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Employees {
    @Id
    private String employeeId;
    private String name;
    private String position;
    private String userName;
    private String password;
    private String phone; // sửa thành String
    private Integer salary;

    @OneToMany(mappedBy = "employee")
    private List<Orders> order;

    @OneToMany(mappedBy = "employee")
    private List<StockImport> stockImport;
}
