package com.example.BaitapJava.Bai6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BaitapJava.Bai6.model.Category;
import com.example.BaitapJava.Bai6.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category get(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}