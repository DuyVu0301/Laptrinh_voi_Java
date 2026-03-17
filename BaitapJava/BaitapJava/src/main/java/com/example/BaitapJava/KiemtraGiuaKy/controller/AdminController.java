package com.example.BaitapJava.KiemtraGiuaKy.controller;

import com.example.BaitapJava.KiemtraGiuaKy.model.Course;
import com.example.BaitapJava.KiemtraGiuaKy.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    // Hiển thị form thêm mới
    @GetMapping("/courses/new")
    public String showNewCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "new_course";
    }

    // Xử lý lưu (Thêm mới & Cập nhật)
    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute("course") Course course, 
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Đường dẫn lưu ảnh thực tế
                String uploadDir = "src/main/resources/static/images/";
                String fileName = imageFile.getOriginalFilename();
                
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                // Lưu tên file vào database
                course.setImage(fileName);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (course.getId() != 0) {
            // Trường hợp sửa: Nếu không chọn ảnh mới, lấy lại ảnh cũ
            Course existingCourse = courseService.getCourseById(course.getId());
            if (existingCourse != null) {
                course.setImage(existingCourse.getImage());
            }
        }
        
        courseService.saveCourse(course);
        return "redirect:/home";
    }

    // Form cập nhật
    @GetMapping("/courses/edit/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    // Xử lý xóa
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") int id) {
        this.courseService.deleteCourseById(id);
        return "redirect:/home";
    }
}