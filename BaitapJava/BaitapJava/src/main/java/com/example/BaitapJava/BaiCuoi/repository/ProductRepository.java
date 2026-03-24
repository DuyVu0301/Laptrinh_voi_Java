package com.example.BaitapJava.BaiCuoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BaitapJava.BaiCuoi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}