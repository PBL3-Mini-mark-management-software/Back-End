package com.example.backend.controller;

import com.example.backend.model.Suppliers;
import com.example.backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.DTO.SupplierRequest;

import java.util.List;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://localhost:5173")
public class SuppliersController {

    @Autowired
    private SupplierService service;

    @GetMapping
    public List<Suppliers> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suppliers> getById(@PathVariable String id) {  
        Suppliers s = service.getById(id);
        return (s!=null) ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Suppliers create(@RequestBody SupplierRequest req) {
        Suppliers s = new Suppliers();
        s.setName(req.name);
        s.setPhone(req.phone);
        s.setAddress(req.address);
        return service.create(s);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Suppliers> path(@PathVariable String id, @RequestBody Suppliers s){
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.update(id, s));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if (!service.existsById(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}