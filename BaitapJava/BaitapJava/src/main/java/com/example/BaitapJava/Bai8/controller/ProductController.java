package com.example.BaitapJava.Bai8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.BaitapJava.Bai8.model.Product;
import com.example.BaitapJava.Bai8.repository.CategoryRepository;
import com.example.BaitapJava.Bai8.repository.ProductRepository;
import com.example.BaitapJava.Bai8.service.CategoryService;
import com.example.BaitapJava.Bai8.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "price") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        // 1. Thiết lập hướng sắp xếp và phân trang (5 sản phẩm/trang)
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, 5, sort);

        // 2. Gọi hàm tìm kiếm tổng hợp từ Repository
        Page<Product> productPage = productRepository.findByFilters(keyword, categoryId, pageable);

        // 3. Gửi dữ liệu sản phẩm và phân trang ra View
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        // 4. QUAN TRỌNG: Gửi danh sách Category để ô Select không bị trống
        model.addAttribute("categories", categoryRepository.findAll());

        // 5. Gửi các thông số Sort và Filter để giữ trạng thái trên giao diện
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);

        return "product/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/edit";
    }

    // Sửa ID từ int sang Long để khớp với JpaRepository trong ảnh lỗi của bạn
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
