package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Customers {
    @Id
    private String customer_id;
    
    private String name;
    private String phone; 

    private LocalDate date_of_birth;
    private Integer points;
    private String membership_type;
    private String customer_group;

    @OneToMany(mappedBy = "customer")
    private List<Orders> order;
}
