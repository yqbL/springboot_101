package com.example.springboot_101.dto;

public class MedicineItemDTO {
    private Long id;
    private String productName;
    private String chemicalName;
    private String dose;
    private String time;

    // 构造函数
    public MedicineItemDTO() {
    }

    public MedicineItemDTO(Long id, String productName, String chemicalName, String dose, String time) {
        this.id = id;
        this.productName = productName;
        this.chemicalName = chemicalName;
        this.dose = dose;
        this.time = time;
    }

    // Getters 和 Setters
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

//    public String getStatus() {
//        return status;
//    }

//    public void setStatus(String status) {
//        this.status = status;
//    }
}
