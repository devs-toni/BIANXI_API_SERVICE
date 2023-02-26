package com.ecommerce.bikes.security;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").permitAll()
				.anyRequest().permitAll()
			);

		return http.build();
	}	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setMaxAge(3600L);
		cors.addAllowedMethod("*");
		cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		cors.setAllowedHeaders(Arrays.asList("Authorization, X-API-KEY, Origin, X-Requested-With, Content-type, Accept, Access-Control-Allow-Request-Method"));
		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
		src.registerCorsConfiguration("/**", cors);
		return src;
	}
}
