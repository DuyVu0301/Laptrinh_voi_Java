package com.example.BaitapJava.Bai8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BaitapJava.Bai8.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}