package com.example.BaitapJava.KiemtraGiuaKy.repository;

import com.example.BaitapJava.KiemtraGiuaKy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}