package com.example.BaitapJava.Bai8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BaitapJava.Bai8.model.OrderDetail;
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {}