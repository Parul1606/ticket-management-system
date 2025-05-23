package com.tmdb.auth_api.dto;

import lombok.*;

/**
 * UserDetailDto - Data Transfer Object for user credentials.
 *
 * Purpose:
 * This class is used to receive or send user details such as:
 * - Email (used as username or user ID)
 * - Password (used for authentication)
 * - Role (used for authorization)
 *
 * - DTOs are commonly used to separate internal entity models from external API contracts.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailDto {
    String email;
    String password;
    String role;
}
