package com.example.BaitapJava.KiemtraGiuaKy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Câu 4: Phân quyền truy cập
                .requestMatchers("/login", "/register", "/css/**").permitAll()
                .requestMatchers("/courses").permitAll() // Cho tất cả người dùng
                .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN
                .requestMatchers("/enroll/**").hasRole("STUDENT") // Chỉ STUDENT
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                // Câu 5: Thành công chuyển tới /home
                .defaultSuccessUrl("/home", true) 
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }
}