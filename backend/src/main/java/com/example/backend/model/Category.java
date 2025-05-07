package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
public class Category {
    @Id
    private String categoryId;
    private String name;

    @OneToMany(mappedBy = "category")
@JsonIgnoreProperties("category") // tránh lặp ngược lại
private List<Products> products;
}
