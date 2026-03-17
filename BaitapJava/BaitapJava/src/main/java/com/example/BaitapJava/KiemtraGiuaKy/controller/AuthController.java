package com.example.BaitapJava.KiemtraGiuaKy.controller;

import com.example.BaitapJava.KiemtraGiuaKy.model.User;
import com.example.BaitapJava.KiemtraGiuaKy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Đường dẫn trả về trang đăng nhập
    @GetMapping("/login")
    public String login() {
        return "login"; // Phải có file login.html trong templates
    }

    // Đường dẫn trả về trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Phải có file register.html trong templates
    }

    // Xử lý lưu user khi bấm nút "Đăng ký ngay"
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login"; // Sau khi lưu xong, tự chuyển hướng sang trang login
    }
    
}