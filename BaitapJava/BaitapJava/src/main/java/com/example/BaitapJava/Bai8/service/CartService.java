package com.example.BaitapJava.Bai8.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.BaitapJava.Bai8.model.CartItem;
import com.example.BaitapJava.Bai8.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope // Giỏ hàng lưu trong Session của từng người dùng
public class CartService {
    private Map<Long, CartItem> maps = new HashMap<>();

    public void add(Product product) {
        CartItem item = maps.get(product.getId());
        if (item == null) {
            item = new CartItem(product.getId(), product.getName(), product.getPrice(), 1);
            maps.put(product.getId(), item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void remove(Long id) {
        maps.remove(id);
    }

    public Collection<CartItem> getItems() {
        return maps.values();
    }

    public double getAmount() { // Tính tổng tiền
        return maps.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
    public void removeOne(Long id) {
        CartItem item = maps.get(id);
        if (item != null) {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                maps.remove(id); // Nếu còn 1 mà trừ tiếp thì xóa luôn
            }
        }
    }

    public void clear() {
        maps.clear();
    }
}