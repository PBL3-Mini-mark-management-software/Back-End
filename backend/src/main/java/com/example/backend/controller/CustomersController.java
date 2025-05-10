
package com.example.backend.controller;

import com.example.backend.model.Customers;
import com.example.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.DTO.CustomerRequest;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomersController {



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
    public Customers create(@RequestBody CustomerRequest req) {
        Customers c = new Customers();
        c.setName(req.name);
        c.setPhone(req.phone);
        c.setDate_of_birth(req.date_of_birth);
        c.setPoints(req.points);
        // c.setMembership_type(req.membership_type);
        
        return service.create(c);
    }
    

    //UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Customers> path(@PathVariable String id, @RequestBody Customers c) {
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.update(id, c));
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
