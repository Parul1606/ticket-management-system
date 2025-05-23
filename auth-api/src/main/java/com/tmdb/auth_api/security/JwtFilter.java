package com.tmdb.auth_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component

// JwtFilter extends OncePerRequestFilter -> ensures this filter runs once per request
public class JwtFilter extends OncePerRequestFilter {


    // /verify-token -> protected
    // /generate-token -> not protected


    // Autowire JwtUtil service for verifying and decrypting token
    @Autowired
    JwtUtil jwtUtil;


    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Get Authorization header from incoming request
        String bearerToken = request.getHeader("Authorization");

        // Check if header is present and starts with "Bearer "
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){

            // Extract JWT token by removing "Bearer " prefix
            String token = bearerToken.substring(7);

            // We got the token now we need to validate that this token is a genuine token or not.
            boolean isValid = jwtUtil.verifyToken(token);

            // If token is invalid, continue filter without setting authentication (reject request silently)
            if(isValid == false){
                // I am not going to set any kind of authentication and i will return from here it self
                // before filtering if i am not setting any kind of authetication that
                // means i am rejecting the reuquest
                filterChain.doFilter(request, response);
                return;
            }

            // If valid, decrypt token to get user credentials (e.g., username or email)
            String crednetials = jwtUtil.decryptToken(token);

            // Create authentication token using the user credentials
            // Parameters: principal (username), credentials (null since verified), authorities (empty list for roles)
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(crednetials, null, Collections.emptyList());

            // Set authentication in the security context so user is marked as authenticated
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue filter chain for further processing
        filterChain.doFilter(request, response); // If you are not setting up username and password authenthication that means you are rejecting token
    }

}

// 🔐 This filter checks JWT token in the request header:
// ✅ If valid → set user as authenticated
// ❌ If invalid/missing → don't set auth, let the request pass unverified

/*
 -----------------------------------------
 Keyword Reference Table - JWT Filter Code
 -----------------------------------------
 OncePerRequestFilter              => Runs filter logic only once per request
 HttpServletRequest / HttpServletResponse => Objects carrying request and response details
 FilterChain                       => Passes request to next filter or controller
 Authorization header             => Header where JWT token is sent (as "Bearer <token>")
 verifyToken()                    => Checks if token is genuine
 decryptToken()                   => Extracts user info (e.g., username/email) from token
 UsernamePasswordAuthenticationToken => Spring class to represent authenticated user info
 SecurityContextHolder            => Holds current user’s authentication info for the session
 Collections.emptyList()          => No roles/authorities given in this case
*/
