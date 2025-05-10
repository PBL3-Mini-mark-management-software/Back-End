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
        // Lấy giá trị ind lớn nhất từ bảng
        Long maxInd = repo.findMaxInd();
        if (maxInd == null) {
            maxInd = 0L;
        }
        
        Long newInd = maxInd + 1;
        
        // Tạo customer_id với ind mới
        String customerId = "KH" + String.format("%03d", newInd);
        
        // Gán customer_id và ind cho đối tượng Customers
        c.setCustomer_id(customerId);
        c.setInd(newInd);


        // Debug: Kiểm tra xem customer_id có được gán chính xác không
        System.out.println("Generated customer_id: " + customerId);
        System.out.println("Generated ind: " + newInd);

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

    public boolean existsById(String id){
        return repo.existsById(id);
    }

    public boolean existByPhone(String phone){
        return repo.existsByPhone(phone);
    }
}
