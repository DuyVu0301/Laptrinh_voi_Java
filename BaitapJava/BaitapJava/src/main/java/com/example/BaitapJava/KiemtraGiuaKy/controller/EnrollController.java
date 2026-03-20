package com.example.BaitapJava.KiemtraGiuaKy.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BaitapJava.KiemtraGiuaKy.service.CourseService;

@Controller
@RequestMapping("/enroll")
public class EnrollController {
    
    @Autowired
    private CourseService courseService;
    @GetMapping("/{id}")
    public String enrollCourse(@PathVariable("id") int courseId, Principal principal) {
        String username = principal.getName();
        System.out.println("Sinh viên " + username + " đã đăng ký môn ID: " + courseId);
        
        return "redirect:/home?enrolled=true";
    }
}