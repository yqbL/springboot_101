package com.example.springboot_101.util;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.dto.MedicineOrderDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static MedicineOrderDTO convertToMedicineOrderDTO(MedicineOrder medicineOrder) {
        if (medicineOrder == null) {
            return null;
        }
        List<MedicineItemDTO> itemDTOs = new ArrayList<>();
        if (medicineOrder.getItems() != null) {
            for (MedicineItem item : medicineOrder.getItems()) {
                itemDTOs.add(convertToMedicineItemDTO(item));
            }
        }
        User user = medicineOrder.getUser();
        Long userId = user != null? user.getId() : null;
        String userPhone = user != null? user.getPhone() : null;
        return new MedicineOrderDTO(
                medicineOrder.getId(),
                null, // 这里假设 hospital 暂时不需要处理
                itemDTOs,
                userId,
                userPhone
        );
    }

    public static MedicineItemDTO convertToMedicineItemDTO(MedicineItem medicineItem) {
        if (medicineItem == null) {
            return null;
        }
        return new MedicineItemDTO(
                medicineItem.getId(),
                medicineItem.getProductName(),
                medicineItem.getChemicalName(),
                medicineItem.getDose(),
                medicineItem.getTime(),
                medicineItem.getStatus()
        );
    }

    public static List<MedicineOrderDTO> convertToMedicineOrderDTOList(List<MedicineOrder> medicineOrders) {
        if (medicineOrders == null) {
            return null;
        }
        List<MedicineOrderDTO> medicineOrderDTOs = new ArrayList<>();
        for (MedicineOrder medicineOrder : medicineOrders) {
            medicineOrderDTOs.add(convertToMedicineOrderDTO(medicineOrder));
        }
        return medicineOrderDTOs;
    }

    public static MedicineItem convertToMedicineItem(MedicineItemDTO medicineItemDTO) {
        if (medicineItemDTO == null) {
            return null;
        }
        MedicineItem medicineItem = new MedicineItem();
        medicineItem.setId(medicineItemDTO.getId());
        medicineItem.setProductName(medicineItemDTO.getProductName());
        medicineItem.setChemicalName(medicineItemDTO.getChemicalName());
        medicineItem.setDose(medicineItemDTO.getDose());
        medicineItem.setTime(medicineItemDTO.getTime());
        medicineItem.setStatus(medicineItemDTO.getStatus());
        return medicineItem;
    }
}