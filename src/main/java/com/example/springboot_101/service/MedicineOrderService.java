package com.example.springboot_101.service;

import com.example.springboot_101.model.Doctor;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.repository.DoctorRepository;
import com.example.springboot_101.repository.MedicineItemRepository;
import com.example.springboot_101.repository.MedicineOrderRepository;
import com.example.springboot_101.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MedicineOrderService {

    @Autowired
    private MedicineOrderRepository medicineOrderRepository;

    @Autowired
    private MedicineItemRepository medicineItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public MedicineOrder createMedicineOrder(Long userId, Long doctorId, List<MedicineItem> medicineItems) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("医生不存在"));

        MedicineOrder medicineOrder = new MedicineOrder();
        medicineOrder.setUser(user);
        medicineOrder.setDoctor(doctor);
        medicineOrder.setOrderTime(new Date());

        List<MedicineItem> savedItems = new ArrayList<>();
        for (MedicineItem item : medicineItems) {
            item.setMedicineOrder(medicineOrder);
            savedItems.add(medicineItemRepository.save(item));
        }
        medicineOrder.setItems(savedItems);

        return medicineOrderRepository.save(medicineOrder);
    }

    // 获取所有药单
    public List<MedicineOrder> getAllMedicineOrders() {
        return medicineOrderRepository.findAll();
    }

    public MedicineOrder getMedicineOrderById(Long id) {
        return medicineOrderRepository.findById(id).orElse(null);
    }

    // 根据用户 ID 获取药单列表
    public List<MedicineOrder> getMedicineOrdersByUserId(Long userId) {
        return medicineOrderRepository.findByUserId(userId);
    }

    // 新增方法：保存药单
    public MedicineOrder saveMedicineOrder(MedicineOrder medicineOrder) {
        return medicineOrderRepository.save(medicineOrder);
    }

    public List<MedicineOrder> getMedicineOrdersByDoctorId(Long doctorId) {
        return medicineOrderRepository.findByDoctorId(doctorId);
    }
}