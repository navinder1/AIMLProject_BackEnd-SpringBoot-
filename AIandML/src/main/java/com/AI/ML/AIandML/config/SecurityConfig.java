package com.AI.ML.AIandML.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            // Disable CSRF (important for Postman)
            .csrf(csrf -> csrf.disable())

            // Disable default login page
            .formLogin(form -> form.disable())

            // Disable basic auth popup
            .httpBasic(basic -> basic.disable())

            // Allow authentication APIs
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**")
                    .permitAll()

                    .anyRequest()
                    .authenticated()
            );

        return http.build();
    }
}