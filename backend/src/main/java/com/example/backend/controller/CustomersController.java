package com.example.backend.controller;

import com.example.backend.model.Customers;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
// @CrossOrigin(origins = "*") 
@CrossOrigin(origins = "http://localhost:5173")
public class CustomersController {

    // @Autowired
    // private CustomerRepository customersRepository;

    // // In ra tất cả khách hàng
    // @GetMapping
    // public List<Customers> getAllCustomers() {
    //     return customersRepository.findAll();
    // }

    // // Lấy 1 khách hàng theo ID (do bạn nhập thủ công)
    // @GetMapping("/{id}")
    // public Customers getCustomerById(@PathVariable String id) {
    //     return customersRepository.findById(id).orElse(null);
    // }

    @Autowired
    private CustomerService service;

    //READ ALL
    @GetMapping
    public List<Customers> getAll() {
        return service.getAll();
    }

    
    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customers> getById(@PathVariable String id) {
        Customers c = service.getById(id);
        return (c!=null)? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    //CREATE
    @PostMapping
    public Customers create(@RequestBody Customers c) {
        return service.create(c);
    }
    

    //UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Customers> path(@PathVariable String id, @RequestBody Customers c) {
        if (!service.exists(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.update(id, c));
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if (!service.exists(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
