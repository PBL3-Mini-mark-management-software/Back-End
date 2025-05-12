package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Employees {
    @Id
    private String employee_id;

    @Column(unique = true)
    private String phone; 


    private String name;
    private String position;
    private String user_name;
    private String password;
    private Integer salary;
    private LocalDate date_of_birth;

    @Column(unique = true)
    private Long ind; 

    @OneToMany(mappedBy = "employee")
    private List<Orders> order;

    @OneToMany(mappedBy = "employee")
    private List<StockImport> stockImport;
}
