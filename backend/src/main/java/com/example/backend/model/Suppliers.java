package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Suppliers {
    @Id
    private String supplier_id;
    private String name;
    
    @Column(unique = true)
    private String phone;
    
    private String address;

    @Column(unique = true)
    private Long ind; 

    @OneToMany(mappedBy = "supplier")
    private List<Products> product;
}
