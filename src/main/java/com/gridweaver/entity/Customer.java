package com.gridweaver.entity;

import jakarta.persistence.*;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String email;

    private String phoneNumber;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Battery> batteries;

    public Customer() {
    }

    public Customer(Long id, String customerName, String email, String phoneNumber) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}