package com.example.BaitapJava.Bai8.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Tên danh mục không được để trống")
    @Column(nullable = false, length = 255)
    private String name;
}