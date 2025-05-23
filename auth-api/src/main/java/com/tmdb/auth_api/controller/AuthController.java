package com.tmdb.auth_api.controller;

import com.tmdb.auth_api.dto.StatusDto;
import com.tmdb.auth_api.dto.UserDetailDto;
import com.tmdb.auth_api.security.JwtUtil;
import com.tmdb.auth_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ✅ AuthController - Exposes REST endpoints for JWT-based authentication
 *
 * 🔐 This controller handles:
 * - Token Generation
 * - Token Verification
 * - Access Permission Check for a specific operation
 */

@RestController
@RequestMapping("/api/v1/auth/token")  // Base URL for all endpoints in this controller
public class AuthController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthService authService;

    /**
     * ✅ Endpoint: /api/v1/auth/token/get
     * 🔄 Method: GET
     * 📌 Purpose: Generates a JWT token from user credentials (email, password, role)
     * 📥 Input: UserDetailDto as request body
     * 📤 Output: JWT token (String)
     */
    @GetMapping("/get")
    public ResponseEntity generateToken(@RequestBody UserDetailDto userDetails){
        // Create token using provided credentials
        String token = jwtUtil.generateToken(userDetails.getEmail(), userDetails.getPassword(), userDetails.getRole());
        return new ResponseEntity(token, HttpStatus.OK);
    }

    /**
     * ✅ Endpoint: /api/v1/auth/token/verify
     * 🔄 Method: GET
     * 📌 Purpose: Stub endpoint that always returns "valid: true"
     * 📝 This could be expanded in future to perform full token validation
     */
    @GetMapping("/verify")
    public ResponseEntity verifyToken(){   //we may use if we want, but we don't require this "@RequestHeader String Authorization"
        StatusDto statusDto = new StatusDto();
        statusDto.setValid(true);
        return new ResponseEntity(statusDto, HttpStatus.OK);
    }

    /**
     * ✅ Endpoint: /api/v1/auth/token/verify/operation/access
     * 🔄 Method: GET
     * 📌 Purpose: Check if the user has permission to perform a specific operation on an organization
     * 📥 Input:
     *  - operationName: Action the user wants to perform
     *  - orgId: UUID of the organization
     *  - Authorization header: Bearer token
     * 📤 Output: StatusDto with validity info
     */
    @GetMapping("/verify/operation/access")
    public ResponseEntity verifyOperationAccess(@RequestParam String operationName,
                                                @RequestParam UUID orgId,
                                                @RequestHeader String Authorization){

        // 🔐 Extract token by removing "Bearer " prefix
        String token = Authorization.substring(7);

        // 🔎 Check if user has access to the specified operation
        boolean result = authService.isValidAccess(token, orgId, operationName);

        // 📤 Prepare response DTO
        StatusDto statusDto = new StatusDto();
        statusDto.setValid(result);

        // 🚫 If user is not authorized for this operation, return 401 Unauthorized
        if(result == false){
            return new ResponseEntity(statusDto, HttpStatus.UNAUTHORIZED);
        }

        // ✅ Authorized → return OK with valid: true
        return new ResponseEntity(statusDto, HttpStatus.OK);

    }

}

/*
 ---------------------------------------------
 🧠 Keyword Reference Table - AuthController
 ---------------------------------------------
 @RestController            => Combines @Controller + @ResponseBody for REST APIs
 @RequestMapping            => Maps base path for controller
 @RequestBody               => Extracts JSON body into Java object
 @RequestParam              => Reads query parameters from URL
 @RequestHeader             => Reads HTTP headers (e.g., Authorization)
 ResponseEntity             => Full HTTP response including status and body
 StatusDto                  => Custom DTO indicating if a token/access is valid
 authService.isValidAccess  => Business logic to check access permission
 jwtUtil.generateToken()    => Creates a signed JWT with user credentials
*/
