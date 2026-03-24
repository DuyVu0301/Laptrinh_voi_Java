package com.example.BaitapJava.Bai8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.BaitapJava.Bai8.service.AccountService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AccountService accountService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình AuthenticationManager sử dụng AccountService
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Cho phép tất cả mọi người (kể cả chưa đăng nhập) xem danh sách sản phẩm
                        .requestMatchers("/products").permitAll()

                        // 2. Các tài nguyên tĩnh (hình ảnh, css, js) cũng nên permitAll để giao diện không lỗi
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // 3. Các chức năng quản lý (Thêm/Sửa/Xóa) bắt buộc là ADMIN
                        // Giả sử các link của bạn có dạng /products/add, /products/edit, /products/delete
                        .requestMatchers("/products/add/**", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")

                        // 4. Các chức năng liên quan đến Mua hàng/Giỏ hàng (Cart)
                        // Khi bấm vào "Mua", hệ thống sẽ tự check xem đã đăng nhập chưa
                        .requestMatchers("/cart/**", "/checkout/**").authenticated()

                        // 5. Tất cả các yêu cầu khác đều cần xác thực
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());

        return http.build();
    }
}