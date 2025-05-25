package com.ticket_management_system.database_api.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    @Column(unique = true)
    String registeredName;
    String websiteUrl;
    int companySize;
    @Column(unique = true)
    String adminEmail;
    String adminName;
    String password;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
