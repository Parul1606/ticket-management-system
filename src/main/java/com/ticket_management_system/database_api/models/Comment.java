package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    //@GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String commentDescription;
    @ManyToOne
    Task task;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
