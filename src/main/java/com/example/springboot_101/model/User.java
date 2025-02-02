package com.example.springboot_101.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String password;
    private String role; // 1 代表医生 0 代表普通用户
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Doctor doctor;

    // 可以添加与 MedicineOrder 的关联关系，这里使用 OneToMany 表示一个用户可以有多个药单
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicineOrder> medicineOrders;



    // 默认构造方法
    public User() {
    }

    // 构造方法
    public User(String phone, String password, String role) {
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 新增 MedicineOrders 的 Getter 和 Setter
    public List<MedicineOrder> getMedicineOrders() {
        return medicineOrders;
    }

    public void setMedicineOrders(List<MedicineOrder> medicineOrders) {
        this.medicineOrders = medicineOrders;
    }
}