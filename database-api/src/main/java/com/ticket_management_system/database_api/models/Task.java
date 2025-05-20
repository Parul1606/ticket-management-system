package com.ticket_management_system.database_api.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    String description;
    @ManyToOne
    Employee assignee;
    @ManyToOne
    Project project;
    int points;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
