package com.tmdb.auth_api.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    UUID id;
    String name;
    Organization organization;
    List<Operation> operations;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
