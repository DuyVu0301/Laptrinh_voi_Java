package com.example.BaitapJava.KiemtraGiuaKy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "STUDENT") // Lưu vào bảng STUDENT theo yêu cầu
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullname;

    @Column(nullable = false)
    private String role = "STUDENT"; // Quyền mặc định là STUDENT
}