package com.example.springboot_101.repository;

import com.example.springboot_101.model.MedicineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {
    // 根据药单编号查询对应的所有药品条目
    List<MedicineItem> findByMedicineOrderId(Long orderId);
}