package com.example.BaitapJava.KiemtraGiuaKy.service;

import com.example.BaitapJava.KiemtraGiuaKy.model.User;
import com.example.BaitapJava.KiemtraGiuaKy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng");
        }
        
        // Trả về đối tượng User của Spring Security
        // Lưu ý: Vai trò phải có tiền tố "ROLE_" (ví dụ: ROLE_STUDENT, ROLE_ADMIN)
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}