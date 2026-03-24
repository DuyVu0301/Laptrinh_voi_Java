package com.example.BaitapJava.BaiCuoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BaitapJava.BaiCuoi.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}