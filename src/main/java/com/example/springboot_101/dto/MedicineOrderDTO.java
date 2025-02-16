package com.example.springboot_101.dto;

import java.util.Date;
import java.util.List;

public class MedicineOrderDTO {
    private Long id;
    private String time;
    private String hospital;
    private Long userId;
    private String userPhone;

    public MedicineOrderDTO(Long id, String hospital, Long userId, String userPhone,String time) {
        this.id = id;
        this.hospital = hospital;
        this.userId = userId;
        this.userPhone = userPhone;
        this.time = time;
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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