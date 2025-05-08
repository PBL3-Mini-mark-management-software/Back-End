package com.example.backend.service;

import com.example.backend.model.Customers;
import com.example.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repo;

    public List<Customers> getAll(){
        return repo.findAll();
    }

    public Customers getById(String id){
        return repo.findById(id).orElse(null);
    }

    public Customers create(Customers c){
        return repo.save(c);
    }

    public Customers update(String id, Customers c){
        // c.setCustomer_id(id);
        // return repo.save(c);
        Customers existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        if (c.getName()!=null) existing.setName(c.getName());
        if (c.getPhone()!=null) existing.setPhone(c.getPhone());
        if (c.getDate_of_birth()!=null) existing.setDate_of_birth(c.getDate_of_birth());
        if (c.getPoints()!=null) existing.setPoints(c.getPoints());
        if (c.getMembership_type()!=null) existing.setMembership_type(c.getMembership_type());
        if (c.getCustomer_group()!=null) existing.setCustomer_group(c.getCustomer_group());
        return repo.save(existing);
    }

    public void delete(String id){
        repo.deleteById(id);
    }

    public boolean exists(String id){
        return repo.existsById(id);
    }
}
