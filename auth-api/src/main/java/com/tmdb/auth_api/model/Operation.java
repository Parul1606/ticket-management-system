package com.tmdb.auth_api.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation {

    UUID id;
    String name;
    String description;
}
