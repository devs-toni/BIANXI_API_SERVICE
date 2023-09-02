package com.ecommerce.bikes.security;


//import com.ecommerce.bikes.adapters.*;
//import com.ecommerce.bikes.ports.*;

import com.ecommerce.bikes.adapters.*;
import com.ecommerce.bikes.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll()
                ).csrf().disable();

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE"));
        cors.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://localhost:3000"));
        cors.setAllowedHeaders(Arrays.asList("Authorization", "X-API-KEY", "Origin", "X-Requested-With", "Content-Type", "Accept", "Access-Control-Allow-Request-Method"));
        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cors);
        return src;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort(OrderAdapter orderAdapter) {
        return orderAdapter;
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserAdapter userAdapter) {
        return userAdapter;
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductAdapter productAdapter) {
        return productAdapter;
    }

    @Bean
    public ColorRepositoryPort colorRepositoryPort(ColorAdapter colorAdapter) {
        return colorAdapter;
    }

    @Bean
    public SizeRepositoryPort sizeRepositoryPort(SizeAdapter sizeAdapter) {
        return sizeAdapter;
    }
}

