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

    private int getLevel(String membership) {
        if (membership == null) return 1;

        return switch (membership) {
            case "Kim cương" -> 5;
            case "Vàng" -> 4;
            case "Bạc" -> 3;
            case "Đồng" -> 2;
            case "Chưa có hạng" -> 1;
            default -> 0;
        };
    }



    //nâng cấp, update membership_type
    public String determineMembershipType(Integer points, String currentMembership) {
        if (points == null) return currentMembership != null ? currentMembership : "Chưa có hạng";

        String newMembership;
        if (points > 5000) newMembership = "Kim cương";
        else if (points > 2000) newMembership = "Vàng";
        else if (points > 700) newMembership = "Bạc";
        else if (points > 100) newMembership = "Đồng";
        else newMembership = "Chưa có hạng";

        // Xác định thứ hạng
        int currentLevel = getLevel(currentMembership);
        int newLevel = getLevel(newMembership);

        return (newLevel > currentLevel) ? newMembership : currentMembership;
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
        c.setMembership_type(determineMembershipType(c.getPoints(), null));

        // Debug: Kiểm tra xem customer_id có được gán chính xác không
        System.out.println("Generated customer_id: " + customerId);
        System.out.println("Generated ind: " + newInd);

        return repo.save(c);  
    }

    
    public Customers update(String id, Customers c){
        Customers existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        if (c.getName()!=null) existing.setName(c.getName());
        if (c.getPhone()!=null) existing.setPhone(c.getPhone());
        if (c.getDate_of_birth()!=null) existing.setDate_of_birth(c.getDate_of_birth());
        if (c.getCustomer_id() != null) existing.setCustomer_id(c.getCustomer_id());

        return repo.save(existing);
    }

    // try update point
    public Customers updatePoints(String id, Integer points){
        Customers existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        if (points != null) {
            existing.setPoints(points);
            String updatedMembership = determineMembershipType(points, existing.getMembership_type());
            existing.setMembership_type(updatedMembership);
        }

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
