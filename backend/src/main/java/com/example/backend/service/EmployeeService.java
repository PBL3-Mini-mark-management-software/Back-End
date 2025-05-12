package com.example.backend.service;

import com.example.backend.model.Employees;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    public List<Employees> getAll(){
        return repo.findAll();
    }

    public Employees getById(String id){
        return repo.findById(id).orElse(null);
    }

    public Employees create(Employees e){
        // Lấy giá trị ind lớn nhất từ bảng
        Long maxInd = repo.findMaxInd();
        if (maxInd == null) {
            maxInd = 0L;
        }
        
        Long newInd = maxInd + 1;
        
        // Tạo customer_id với ind mới
        String employeeId = "NV" + String.format("%03d", newInd);
        
        // Gán customer_id và ind cho đối tượng Customers
        // c.setCustomer_id(customerId);
        e.setEmployee_id(employeeId);
        e.setInd(newInd);

        return repo.save(e);  
    }

    
    public Employees update(String id, Employees e){
        Employees existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        if (e.getName()!=null) existing.setName(e.getName());
        if (e.getPhone()!=null) existing.setPhone(e.getPhone());
        if (e.getDate_of_birth()!=null) existing.setDate_of_birth(e.getDate_of_birth());
        if (e.getEmployee_id() != null) existing.setEmployee_id(e.getEmployee_id());
        if (e.getUser_name() != null) existing.setUser_name(e.getUser_name());
        if (e.getPassword() != null) existing.setPassword(e.getPassword());
        if (e.getPosition() != null) existing.setPosition(e.getPosition());
        if (e.getSalary() != null) existing.setSalary(e.getSalary());


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