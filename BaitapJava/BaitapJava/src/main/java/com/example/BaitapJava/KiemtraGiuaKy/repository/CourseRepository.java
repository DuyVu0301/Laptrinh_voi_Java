package com.example.BaitapJava.KiemtraGiuaKy.repository;

import com.example.BaitapJava.KiemtraGiuaKy.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Spring Data JPA đã tự động cung cấp các hàm save, findById, deleteById
}