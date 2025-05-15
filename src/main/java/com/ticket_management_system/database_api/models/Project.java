package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String description;
    @OneToMany
    List<Employee> employees;
    @ManyToOne
    Organization organization;
    @OneToMany
    List<Operation> operations;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
