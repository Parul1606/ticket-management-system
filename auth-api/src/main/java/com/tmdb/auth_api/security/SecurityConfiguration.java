package com.tmdb.auth_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// SecurityConfiguration defines Spring Security rules for your application
public class SecurityConfiguration {

    // Autowire JwtFilter to inject it into the Spring Security filter chain
    @Autowired
    JwtFilter jwtFilter;

    /**
     * Configures the security filter chain.
     * - Disables CSRF protection (useful for stateless APIs)
     * - Defines which endpoints are public and which are protected
     * - Configures session to be stateless
     * - Adds custom JWT filter before Spring's default authentication filter
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                // Define access rules for endpoint
                .authorizeHttpRequests(
                        // Allow unauthenticated access to token generation endpoint
                        auth -> auth.requestMatchers(
                                        "/api/v1/auth/token/get"
                                ).permitAll()
                                // All other requests require authentication
                                .anyRequest().authenticated()
                )
                // Make the app stateless – no HTTP session will be created
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Insert jwtFilter before the default UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // Build and return the configured SecurityFilterChain
                .build();
    }
}

/*
 -----------------------------------------
 🔐 SecurityConfiguration – Key Concepts
 -----------------------------------------
 JwtFilter                        => Custom filter to validate JWT tokens
 SecurityFilterChain             => Defines the full security behavior for HTTP requests
 @Bean                           => Registers the securityFilterChain with Spring context
 HttpSecurity                    => Main configuration object for web security
 .csrf().disable()               => Disables CSRF protection (safe for APIs using JWT)
 .authorizeHttpRequests()        => Defines public/protected endpoints
 .sessionManagement(STATELESS)   => Prevents server-side sessions (suitable for JWT stateless auth)
 .addFilterBefore()              => Injects jwtFilter in correct position in the chain

 -----------------------------------------
 📌 Endpoint Access Configuration
 -----------------------------------------
 "/api/v1/auth/token/get"        => Public endpoint (token generation/login)
 anyRequest().authenticated()    => All other APIs are protected

 -----------------------------------------
 🔁 Filter Chain Flow
 -----------------------------------------
 Incoming HTTP Request
        ⬇
   JwtFilter (validates JWT)
        ⬇
UsernamePasswordAuthenticationFilter (default)
        ⬇
  Controller / Resource Handler
*/