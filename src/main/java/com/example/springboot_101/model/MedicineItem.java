package com.example.springboot_101.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "medicineitem") // 明确指定数据库表名
public class MedicineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String chemicalName;
    private String dose;
    private String time;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore  // 忽略序列化这个字段，防止死循环
    private MedicineOrder medicineOrder;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MedicineOrder getMedicineOrder() {
        return medicineOrder;
    }

    public void setMedicineOrder(MedicineOrder medicineOrder) {
        this.medicineOrder = medicineOrder;
    }
}