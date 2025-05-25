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
@Table(name = "history")
public class ChangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String changeDescription;
    @ManyToOne
    Task task;
    LocalDateTime createdAt;
}
