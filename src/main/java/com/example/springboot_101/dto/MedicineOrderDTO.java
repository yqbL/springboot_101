package com.example.springboot_101.dto;

import java.util.Date;
import java.util.List;

public class MedicineOrderDTO {
    private Long id;
    private Date time;
    private String hospital;
    private List<MedicineItemDTO> items;
    private Long userId;
    private String userPhone;

    public MedicineOrderDTO(Long id, String hospital, List<MedicineItemDTO> items, Long userId, String userPhone) {
        this.id = id;
        this.hospital = hospital;
        this.items = items;
        this.userId = userId;
        this.userPhone = userPhone;
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<MedicineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MedicineItemDTO> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}