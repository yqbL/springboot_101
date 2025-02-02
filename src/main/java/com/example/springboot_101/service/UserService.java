package com.example.springboot_101.service;

import com.example.springboot_101.model.User;
import com.example.springboot_101.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 登录方法
    public User login(String phone, String password) {
        User user = userRepository.findByPhone(phone);
        if (user!= null && user.getPassword().equals(password)) {
            return user; // 登录成功
        }
        return null; // 登录失败
    }

    // 注册方法
    public boolean existsByPhone(String phone) {
        // 检查手机号是否已存在
        return userRepository.findByPhone(phone)!= null;
    }

    public User register(String phone, String password, String userRole) {
        // 创建新用户并保存到数据库，同时设置用户角色
        User user = new User(phone, password, userRole);
        return userRepository.save(user);
    }

    public User findByPhone(String phone) {
        // 根据手机号查找用户
        return userRepository.findByPhone(phone);
    }

    // 新增 findById 方法
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}