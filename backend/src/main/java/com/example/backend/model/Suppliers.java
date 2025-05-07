package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
public class Suppliers {
    @Id
    private String supplierId;
    private String name;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "supplier")
@JsonIgnoreProperties("supplier")
private List<Products> products;

}
