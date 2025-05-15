package com.tmdb.central_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrgDetailDto {

    String name;
    String registeredName;
    String websiteUrl;
    int companySize;
    String adminEmail;
    String adminName;
    String password;
    String address;
}
