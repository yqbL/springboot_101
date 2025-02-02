package com.example.springboot_101.service;

import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.repository.MedicineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineItemService {

    @Autowired
    private MedicineItemRepository medicineItemRepository;

    public List<MedicineItem> getMedicineItemsByOrderId(Long orderId) {
        return medicineItemRepository.findByMedicineOrderId(orderId);
    }
}