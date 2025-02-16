package com.example.springboot_101.util;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.dto.MedicineOrderDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoConverter {

    public static MedicineOrderDTO convertToMedicineOrderDTO(MedicineOrder medicineOrder) {

        String hospital = medicineOrder.getHospital();
        User user = medicineOrder.getUser();
        Long userId = user != null? user.getId() : null;
        String userPhone = user != null? user.getPhone() : null;
        Date time = medicineOrder.getTime();

        // 格式化时间
        String formattedTime = null;
        if (time != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
            formattedTime = dateFormat.format(time);
        }

        return new MedicineOrderDTO(
                medicineOrder.getId(),
                hospital,
                userId,
                userPhone,
                formattedTime // 返回格式化后的时间字符串
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
                medicineItem.getTime()
        );
    }

    public static List<MedicineOrderDTO> convertToMedicineOrderDTOList(List<MedicineOrder> medicineOrders) {

        List<MedicineOrderDTO> medicineOrderDTOs = new ArrayList<>();
        for (MedicineOrder medicineOrder : medicineOrders) {
            medicineOrderDTOs.add(convertToMedicineOrderDTO(medicineOrder));
        }
        return medicineOrderDTOs;
    }

    public static MedicineItem convertToMedicineItem(MedicineItemDTO medicineItemDTO) {

        MedicineItem medicineItem = new MedicineItem();
        medicineItem.setId(medicineItemDTO.getId());
        medicineItem.setProductName(medicineItemDTO.getProductName());
        medicineItem.setChemicalName(medicineItemDTO.getChemicalName());
        medicineItem.setDose(medicineItemDTO.getDose());
        medicineItem.setTime(medicineItemDTO.getTime());

        return medicineItem;
    }
}