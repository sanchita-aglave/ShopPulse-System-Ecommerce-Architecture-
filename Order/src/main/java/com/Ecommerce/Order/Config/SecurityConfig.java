package com.Ecommerce.Order.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

    @Configuration
    public class SecurityConfig {


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            return http
                    .csrf(csrf -> csrf.disable())

                    .sessionManagement(session ->
                            session.sessionCreationPolicy(
                                    SessionCreationPolicy.STATELESS
                            )
                    )

                    .authorizeHttpRequests(auth -> auth

                            // Create order API
                            .requestMatchers("/Order/createOrder")
                            .permitAll()

                            // Other order APIs
                            .requestMatchers("/Order/**")
                            .permitAll()

                            .anyRequest()
                            .permitAll()
                    )

                    .build();
        }
    }

