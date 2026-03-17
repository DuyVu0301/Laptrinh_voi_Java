package com.example.BaitapJava.KiemtraGiuaKy.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String image;
    private int credits;
    private String lecturer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}