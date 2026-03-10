package com.example.BaitapJava.Bai4.controller;

import com.example.BaitapJava.Bai4.model.Product;
import com.example.BaitapJava.Bai4.service.CategoryService;
import com.example.BaitapJava.Bai4.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("listproduct", productService.getAll()); 
        return "product/products"; 
    }
    @GetMapping("/create") 
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll()); 
        return "product/create"; 
    }

    @PostMapping("/create")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }
        
        productService.updateImage(product, imageProduct);
        productService.add(product); 
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.get(id);
        if (product == null) {
            return "error/404";
        }
        
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll()); 
        return "product/edit"; 
    }
    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }
        
        if (imageProduct != null && !imageProduct.isEmpty()) {
            productService.updateImage(product, imageProduct);
        }
        
        productService.add(product); 
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id); 
        return "redirect:/products";
    }
}