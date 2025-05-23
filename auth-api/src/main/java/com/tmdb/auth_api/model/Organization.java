package com.tmdb.auth_api.model;

import lombok.*;

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
