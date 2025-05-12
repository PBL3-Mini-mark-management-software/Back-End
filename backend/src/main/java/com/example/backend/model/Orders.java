package com.example.backend.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    private String orderId;

    private Timestamp orderDate;
    private Integer totalAmount;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    @OneToMany(mappedBy = "order")
    private List<Order_details> order_detail;
}
//Linh
package com.example.demo.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Orders {
    @Id
    private String order_id;

    private Timestamp order_date;
    private Long total_amount;
    private Integer voucher;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = true)
    private Customers customer;

    @OneToMany(mappedBy = "order")
    private List<Order_details> order_detail;

    // Getter and Setter for order_id
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    // Getter and Setter for order_id
    public Integer getVoucher() {
        return voucher;
    }

    public void setVoucher(Integer voucher) {
        this.voucher = voucher;
    }

    // Getter and Setter for order_date
    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    // Getter and Setter for total_amount
    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    // Getter and Setter for employee
    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    // Getter and Setter for customer
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    // Getter and Setter for order_detail
    public List<Order_details> getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(List<Order_details> order_detail) {
        this.order_detail = order_detail;
    }
}
