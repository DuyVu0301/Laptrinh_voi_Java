package com.example.BaitapJava.Bai4.service;

import com.example.BaitapJava.Bai4.model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service // BẮT BUỘC phải có dòng này để @Autowired ở Controller hoạt động
public class CategoryService {
    private List<Category> listCategory = new ArrayList<>();

    public CategoryService() {
        // Thêm dữ liệu mẫu để chạy thử
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Máy tính"));
    }

    public List<Category> getAll() { // Tên hàm phải viết đúng getAll
        return listCategory;
    }

    public Category get(int id) {
        return listCategory.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}