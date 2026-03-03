package com.example.BaitapJava.Bai4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BaitapJava.Bai4.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}