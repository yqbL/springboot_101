package com.example.springboot_101.service;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.repository.MedicineItemRepository;
import com.example.springboot_101.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineItemService {

    @Autowired
    private MedicineItemRepository medicineItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineOrderService medicineOrderService;

    public List<MedicineItem> getMedicineItemsByOrderId(Long orderId) {
        return medicineItemRepository.findByMedicineOrderId(orderId);
    }

    public MedicineItem saveMedicineItem(MedicineItem medicineItem) {
        return medicineItemRepository.save(medicineItem);
    }


    public MedicineItem createMedicineItem(Long userId, MedicineItemDTO itemDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));

        User patient = userRepository.findById(itemDTO.getId()).orElseThrow(() -> new RuntimeException("患者不存在"));

        String hospital = "中日友好医院";
        MedicineOrder medicineOrder = medicineOrderService.createOrGetMedicineOrder(patient, hospital);

        MedicineItem medicineItem = new MedicineItem();
        medicineItem.setProductName(itemDTO.getProductName());
        medicineItem.setChemicalName(itemDTO.getChemicalName());
        medicineItem.setDose(itemDTO.getDose());
        medicineItem.setTime(itemDTO.getTime());
//        medicineItem.setStatus("待执行");
        medicineItem.setMedicineOrder(medicineOrder);

        return medicineItemRepository.save(medicineItem);
    }

    public MedicineItem updateMedicineItem(Long userId, Long itemId, MedicineItemDTO itemDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
        if (!"1".equals(user.getRole())) {
            throw new RuntimeException("当前用户无权限");
        }

        MedicineItem existingItem = medicineItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("药品信息不存在"));

        existingItem.setProductName(itemDTO.getProductName());
        existingItem.setChemicalName(itemDTO.getChemicalName());
        existingItem.setDose(itemDTO.getDose());
        existingItem.setTime(itemDTO.getTime());
//        existingItem.setStatus(itemDTO.getStatus());

        return medicineItemRepository.save(existingItem);
    }



}