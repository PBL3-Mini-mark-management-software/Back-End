package com.example.backend.DTO;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String product_id;
    private String name;
    private Integer quantity;
    private Integer price;
    private String category_id;
    private String supplier_id;
    private Integer sell;
    private String img;
    private Date expirationDate;
    private String status;
}
