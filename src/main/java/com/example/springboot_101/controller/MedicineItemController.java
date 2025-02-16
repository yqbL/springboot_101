package com.example.springboot_101.controller;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.service.UserService;
import com.example.springboot_101.service.MedicineOrderService;
import com.example.springboot_101.service.MedicineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/medicine-items")
public class MedicineItemController {

    @Autowired
    private MedicineItemService medicineItemService;

    @Autowired
    private MedicineOrderService medicineOrderService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMedicineItem(
            @RequestParam Long userId, // 从请求参数中获取 userId
            @RequestBody List<MedicineItemDTO> medicineItems) { // 直接接收药品项数据

        System.out.println("Received userId: " + userId);
        System.out.println("Received medicineItems: " + medicineItems);

        Map<String, Object> response = new HashMap<>();
        try {
            // 获取 userId
            User user = userService.findById(userId);
            if (user == null) {
                response.put("success", false);
                response.put("message", "用户不存在");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // 创建 MedicineOrder
            MedicineOrder medicineOrder = new MedicineOrder();
            medicineOrder.setUser(user);
            medicineOrder.setTime(new Date());
            medicineOrder.setHospital("中日友好医院"); // 根据需求设置医院名
            medicineOrder = medicineOrderService.saveMedicineOrder(medicineOrder);  // 保存药单

            // 创建并保存 MedicineItem
            List<MedicineItem> savedItems = new ArrayList<>();
            for (MedicineItemDTO itemDTO : medicineItems) {
                MedicineItem medicineItem = new MedicineItem();
                medicineItem.setProductName(itemDTO.getProductName());
                medicineItem.setChemicalName(itemDTO.getChemicalName());
                medicineItem.setDose(itemDTO.getDose());
                medicineItem.setTime(itemDTO.getTime());
                // 设置药单
                medicineItem.setMedicineOrder(medicineOrder);

                savedItems.add(medicineItemService.saveMedicineItem(medicineItem));  // 保存药品项
            }

            response.put("success", true);
            response.put("message", "药品添加成功");
            response.put("data", savedItems);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "创建药品时出错: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Map<String, Object>> updateMedicineItem(
            @RequestHeader("userId") Long userId,
            @PathVariable Long itemId,
            @RequestBody MedicineItemDTO itemDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            MedicineItem updatedItem = medicineItemService.updateMedicineItem(userId, itemId, itemDTO);

            response.put("success", true);
            response.put("message", "药品信息更新成功");
            response.put("data", updatedItem);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "更新药品信息时出错: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}