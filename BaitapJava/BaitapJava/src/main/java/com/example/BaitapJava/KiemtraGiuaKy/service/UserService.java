package com.example.BaitapJava.KiemtraGiuaKy.service;

import com.example.BaitapJava.KiemtraGiuaKy.model.User;
import com.example.BaitapJava.KiemtraGiuaKy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        // MÃ HÓA LÀ BẮT BUỘC
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Luôn đảm bảo có Role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        }
        userRepository.save(user);
    }
}