package com.example.springboot_101.repository;

import com.example.springboot_101.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // 可以根据需要添加自定义查询方法
}