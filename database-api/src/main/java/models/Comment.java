package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String commentDescription;
    @ManyToOne
    Task task;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
