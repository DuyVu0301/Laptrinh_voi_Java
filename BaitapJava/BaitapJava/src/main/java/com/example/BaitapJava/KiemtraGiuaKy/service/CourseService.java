package com.example.BaitapJava.KiemtraGiuaKy.service;

import com.example.BaitapJava.KiemtraGiuaKy.model.Course;
import com.example.BaitapJava.KiemtraGiuaKy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // --- HÀM CHO CÂU 1 (PHÂN TRANG) ---
    public Page<Course> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.courseRepository.findAll(pageable);
    }

    // --- CÁC HÀM CHO CÂU 2 (CRUD) ---
    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourseById(int id) {
        this.courseRepository.deleteById(id);
    }
}