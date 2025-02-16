package com.example.springboot_101.controller;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.dto.MedicineOrderDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.service.MedicineItemService;
import com.example.springboot_101.service.MedicineOrderService;
import com.example.springboot_101.service.UserService;
import com.example.springboot_101.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*; // 确保导入 java.util.List
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineOrderService medicineOrderService;
    @Autowired
    private MedicineItemService medicineItemService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String password = request.get("password");

        // 查询用户
        User user = userService.findByPhone(phone);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "手机号或密码错误"
            ));
        }

        // 登录成功返回数据
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "登录成功",
                "data", Map.of(
                        "userId", user.getId(),
                        "userRole",user.getRole()
                )
        ));
    }

    // 新增方法：用户注册
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String password = request.get("password");
        String role = request.get("userRole");
        if (role == null) {
            role = "user";// 默认用户角色为普通用户
        }
        if (userService.existsByPhone(phone)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "手机号已被注册"
            ));
        }
        User user = userService.register(phone, password, role);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "注册成功",
                "data", Map.of(
                        "userId", user.getId(),
                        "phone", user.getPhone(),
                        "role", user.getRole()
                )
        ));
    }

    // 获取药单列表
    @GetMapping("/medicine-lists")
    public ResponseEntity<Map<String, Object>> getAllMedicineOrders(@RequestHeader("userId") Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {

            User currentUser = userService.findById(userId);

            // 根据用户角色获取药单列表w
            List<MedicineOrder> medicineOrders;
            if ("1".equals(currentUser.getRole())) { // 医生角色 1
                medicineOrders = medicineOrderService.getAllMedicineOrders();
            } else { // 普通用户角色 0
                medicineOrders = medicineOrderService.getMedicineOrdersByUserId(userId);
            }

            List<MedicineOrderDTO> medicineOrderDTOs = DtoConverter.convertToMedicineOrderDTOList(medicineOrders);
            response.put("success", true);
            response.put("message", "获取药单列表成功");
            response.put("data", medicineOrderDTOs);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取药单列表过程中出现错误，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 根据药单编号（id）获取药单详情
    @GetMapping("/medicine-order/{id}")
    public ResponseEntity<Map<String, Object>> getMedicineOrder(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {

            // 获取所有药单项
            List<MedicineItem> medicineItems = medicineItemService.getMedicineItemsByOrderId(id);
            List<MedicineItemDTO> medicineItemDTOList = new ArrayList<>();
            for (MedicineItem medicineItem : medicineItems) {
                MedicineItemDTO medicineItemDTO = DtoConverter.convertToMedicineItemDTO(medicineItem);
                medicineItemDTOList.add(medicineItemDTO);
            }
            response.put("success", true);
            response.put("message", "获取药单详情成功");
            response.put("data", medicineItemDTOList);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取药单详情过程中出现错误，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
