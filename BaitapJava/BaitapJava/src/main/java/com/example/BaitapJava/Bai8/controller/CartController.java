package com.example.BaitapJava.Bai8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.BaitapJava.Bai8.model.CartItem;
import com.example.BaitapJava.Bai8.model.Order;
import com.example.BaitapJava.Bai8.model.OrderDetail;
import com.example.BaitapJava.Bai8.repository.OrderDetailRepository;
import com.example.BaitapJava.Bai8.repository.OrderRepository;
import com.example.BaitapJava.Bai8.service.CartService;
import com.example.BaitapJava.Bai8.service.ProductService;

import java.util.Date;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getAmount());
        return "product/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        cartService.add(productService.getProductById(id));
        return "redirect:/cart";
    }

    // --- TÍNH NĂNG MỚI: TĂNG/GIẢM SỐ LƯỢNG ---

    @GetMapping("/update/plus/{id}")
    public String updatePlus(@PathVariable("id") Long id) {
        // Tận dụng hàm add có sẵn để tăng số lượng lên 1
        cartService.add(productService.getProductById(id));
        return "redirect:/cart";
    }

    @GetMapping("/update/minus/{id}")
    public String updateMinus(@PathVariable("id") Long id) {
        // Gọi hàm giảm số lượng (bạn cần thêm hàm này vào CartService)
        cartService.removeOne(id);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    // ==========================================
    // CÂU 7: XỬ LÝ ĐẶT HÀNG (CHECKOUT)
    // ==========================================
    @GetMapping("/checkout")
    public String checkout() {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart?error=empty";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "Anonymous";

        Order order = new Order();
        order.setCustomerName(username);
        order.setOrderDate(new Date());
        order.setTotalAmount(cartService.getAmount());

        Order savedOrder = orderRepository.save(order);

        for (CartItem item : cartService.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(productService.getProductById(item.getId()));
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getPrice());

            orderDetailRepository.save(detail);
        }

        // Sau khi đặt hàng thành công thì nên dọn dẹp giỏ hàng
        cartService.clear();

        return "redirect:/products?checkout_success=true";
    }
}