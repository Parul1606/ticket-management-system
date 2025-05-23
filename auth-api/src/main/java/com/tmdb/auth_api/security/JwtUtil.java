package com.tmdb.auth_api.security;

import com.tmdb.auth_api.connector.DBAPI;
import com.tmdb.auth_api.model.Employee;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component

// JwtUtil is a utility class to handle JWT token generation, decryption, and verification
public class JwtUtil {

    // Injecting DBAPI dependency to interact with the database (used for verifying credentials)
    @Autowired
    DBAPI dbapi;

    // Secret key to sign the JWT token (injected from application.properties)
    @Value("${auth.secret.password}")
    String secretPassword;

    // Token validity duration (in milliseconds) – here it is set to approx 16 mins (1000000 ms)
    Long expirationTime = 1000000L; // 10 mins


    /**
     * Generates a JWT token containing user info.
     * Information is stored as subject in the format: userId:password:role
     */
    public String generateToken(String userId, String password, String role){ // email & password
        // Concatenate user details as one string
        String information = userId + ":" + password + ":" + role;

        // Create and sign JWT token using HS256 algorithm
        String jwtToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // token expiry
                .setIssuedAt(new Date())   // token issue time
                .signWith(SignatureAlgorithm.HS256, secretPassword) // signing the token
                .setSubject(information)  // storing user info inside token
                .compact();   // generate final token string
        return jwtToken;  //return token
    }

    /**
     * Decrypts the JWT token and extracts the original subject (userId:password:role)
     */
    public String decryptToken(String token){
        String credentials = Jwts.parser().setSigningKey(secretPassword) // provide secret key for verification
                .parseClaimsJws(token) // parse and validate token
                .getBody()
                .getSubject();    // extract subject (user info)
        return credentials;

    }


    /**
     * Verifies if the token is valid and user credentials match database records
     */
    public boolean verifyToken(String token){
        // Decrypt token to get user credentials
        String credentials = this.decryptToken(token);

        // Split the credentials using ":" format => userId, password, role
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];

        // Fetch employee details using email from database (assumes email is unique identifier)
        Employee emp  = dbapi.callGetEmployeeByEmailEndpoint(email);

        // If employee not found in DB → invalid token
        if(emp == null){
            return false;
        }

        // Check if password from token matches the one in DB
        if(emp.getPassword().equals(password)){
            return true;  //valid token
        }
        return false;  //invalid if password doesn't match
    }
}

/*
 -----------------------------------------
 🔐 JWT Utility Class – Key Responsibilities
 -----------------------------------------
 generateToken()  => Create a signed token with expiry containing user details
 decryptToken()   => Decode token to retrieve original user information
 verifyToken()    => Check if token matches with user in DB

 -----------------------------------------
 Keyword Reference Table – JwtUtil Class
 -----------------------------------------
 @Component                   => Tells Spring to treat this class as a Bean
 @Autowired                   => Injects required dependencies
 @Value("${...}")             => Fetches secret key from application config
 Jwts.builder()               => Creates JWT token
 setSubject()                 => Stores user info inside token
 signWith()                   => Signs the token using algorithm + secret
 parseClaimsJws()             => Verifies and reads token
 DBAPI                        => Custom service to fetch employee info from DB
*/