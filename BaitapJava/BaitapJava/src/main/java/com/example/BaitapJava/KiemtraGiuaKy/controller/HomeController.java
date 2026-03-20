package com.example.BaitapJava.KiemtraGiuaKy.controller;

import com.example.BaitapJava.KiemtraGiuaKy.model.Course;
import com.example.BaitapJava.KiemtraGiuaKy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String viewHomePage(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
        int pageSize = 5; 

        Page<Course> page = courseService.findPaginated(pageNo, pageSize);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listCourses", listCourses);

        return "home";
    }
}