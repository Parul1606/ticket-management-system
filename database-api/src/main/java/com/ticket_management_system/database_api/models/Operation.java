package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(unique = true)
    String name;
    String description;
}
