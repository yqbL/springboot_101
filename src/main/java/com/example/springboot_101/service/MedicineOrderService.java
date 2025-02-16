package com.example.springboot_101.service;

import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.repository.MedicineItemRepository;
import com.example.springboot_101.repository.MedicineOrderRepository;
import com.example.springboot_101.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public MedicineOrder createOrGetMedicineOrder(User patient, String hospital) throws ParseException {
        // 查询是否已有未完成的 MedicineOrder
        List<MedicineOrder> existingOrders = medicineOrderRepository.findByUserId(patient.getId());

        if (!existingOrders.isEmpty()) {
            return existingOrders.get(0); // 返回已有的订单
        }



        // 创建新的 MedicineOrder
        MedicineOrder medicineOrder = new MedicineOrder();
        medicineOrder.setUser(patient);
        medicineOrder.setHospital(hospital);
        // 使用 SimpleDateFormat 格式化时间，确保 time 为 Date 类型
        String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formattedTime);  // 将格式化的字符串转换回 Date

        medicineOrder.setTime(time);  // 将 Date 类型的时间传递给 setTime()
        //medicineOrder.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return medicineOrderRepository.save(medicineOrder);
    }




}