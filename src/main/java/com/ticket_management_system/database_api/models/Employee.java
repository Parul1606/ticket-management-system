package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String password;
    @ManyToOne
    Organization organization;
    @OneToMany
    List<Role> roles;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
