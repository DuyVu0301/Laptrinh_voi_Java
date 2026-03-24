package com.example.BaitapJava.Bai8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BaitapJava.Bai8.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm theo tên VÀ Category (nếu categoryId null thì bỏ qua lọc theo id)
    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId)")
    Page<Product> findByFilters(@Param("name") String name,
                                @Param("categoryId") Long categoryId,
                                Pageable pageable);
}