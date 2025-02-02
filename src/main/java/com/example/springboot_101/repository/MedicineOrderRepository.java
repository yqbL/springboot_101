package com.example.springboot_101.repository;

import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineOrderRepository extends JpaRepository<MedicineOrder, Long> {
    List<MedicineOrder> findByUserId(Long userId);
    List<MedicineOrder> findByDoctorId(Long doctorId);
}