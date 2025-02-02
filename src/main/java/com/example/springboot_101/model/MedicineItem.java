package com.example.springboot_101.model;

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
    private String status;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MedicineOrder getMedicineOrder() {
        return medicineOrder;
    }

    public void setMedicineOrder(MedicineOrder medicineOrder) {
        this.medicineOrder = medicineOrder;
    }
}