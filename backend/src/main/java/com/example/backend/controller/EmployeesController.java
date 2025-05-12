package com.example.backend.controller;

import com.example.backend.model.Employees;
import com.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.DTO.EmployeeRequest;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeesController {

    @Autowired
    private EmployeeService service;

    //READ ALL
    @GetMapping
    public List<Employees> getAll() {
        return service.getAll();
    }

    
    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employees> getById(@PathVariable String id) {
        Employees e = service.getById(id);
        return (e!=null)? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    //CREATE
    @PostMapping
    public Employees create(@RequestBody EmployeeRequest req) {
        Employees e = new Employees();
        e.setName(req.name);
        e.setPhone(req.phone);
        e.setDate_of_birth(req.date_of_birth);
        e.setUser_name(req.user_name);
        e.setPassword(req.password);
        e.setPosition(req.position);
        e.setSalary(req.salary);
        return service.create(e);
    }
    

    //UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Employees> path(@PathVariable String id, @RequestBody Employees c) {
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