package com.ticket_management_system.database_api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(unique = true)
    String name;
    @ManyToOne
    Organization organization;
    @OneToMany
    List<Operation> operations;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
