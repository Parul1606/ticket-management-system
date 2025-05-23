package com.tmdb.central_api.models;

import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Organization {
    UUID id;
    String name;
    String registeredName;
    String websiteUrl;
    int companySize;
    String adminEmail;
    String adminName;
    String password;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
