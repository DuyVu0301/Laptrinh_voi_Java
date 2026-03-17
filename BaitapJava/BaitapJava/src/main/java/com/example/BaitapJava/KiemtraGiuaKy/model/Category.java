package com.example.BaitapJava.KiemtraGiuaKy.model; // Phải là package này

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}