package com.example.BaitapJava.BaiCuoi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BaitapJava.BaiCuoi.model.Product;
import com.example.BaitapJava.BaiCuoi.repository.CategoryRepository;
import com.example.BaitapJava.BaiCuoi.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}