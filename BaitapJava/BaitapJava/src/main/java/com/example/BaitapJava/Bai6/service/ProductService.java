package com.example.BaitapJava.Bai4.service;

import com.example.BaitapJava.Bai4.model.Product;
import com.example.BaitapJava.Bai4.repository.ProductRepository; 
import com.example.BaitapJava.Bai4.repository.CategoryRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public Product get(Integer id) { 
        return productRepository.findById(id).orElse(null);
    }
    
    public void delete(Integer id) { 
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        productRepository.save(product);
    }
    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        if (imageProduct != null && !imageProduct.isEmpty()) {
            try {
                Path dirImages = Paths.get("src/main/resources/static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }
                String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFileName);
                
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}